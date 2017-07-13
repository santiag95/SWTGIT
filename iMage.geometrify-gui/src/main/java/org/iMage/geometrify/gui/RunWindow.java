package org.iMage.geometrify.gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;
import org.iMage.geometrify.PictureFilter;
import org.iMage.geometrify.PictureFilter.Memento;
import org.iMage.geometrify.RandomPointGenerator;

/**
 * The run window for Geometrify's GUI.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public class RunWindow extends JDialog {
	private static final long serialVersionUID = 1L;

	private GeometrifyGUI app;

	private JPanel controls = new JPanel();
	private JPanel upperControls = new JPanel();
	private JPanel snapControl = new JPanel();
	private JLabel preview = new JLabel();
	private JSlider slider = new JSlider();
	private JButton saveButton = new JButton("Save");

	private BufferedImage output;

	private PictureFilter filter;

	private List<Memento> mementos;

	private ImageProcessor processor = new ImageProcessor();

	private UpdatePreviewObserver observer;

	/**
	 * Background worker class responsible for generating the output image
	 */
	private class ImageProcessor extends SwingWorker<BufferedImage, BufferedImage> {
		@Override
		protected BufferedImage doInBackground() {
			return filter.apply(app.getOriginalImage(), app.getNumberOfIterations(), app.getNumberOfSamples());
		}

		@Override
		protected void done() {
			try {
				output = get();
				mementos = filter.getMementos();
			} catch (CancellationException | InterruptedException | ExecutionException e) {
				// ignore
			}
			preview.setVisible(true);
			saveButton.setEnabled(true);
			slider.setEnabled(true);
		}
	}

	/**
	 * Creates a new run window.
	 * 
	 * @param app
	 *            the application the window belongs to
	 * @param main
	 *            the main window
	 */
	public RunWindow(GeometrifyGUI app, JFrame main) {
		super(main);
		this.app = app;
		observer = new UpdatePreviewObserver(preview);
		mementos = new ArrayList<>();

		BufferedImage original = app.getOriginalImage();
		filter = app.getFilter();
		filter.setPointGenerator(new RandomPointGenerator(original.getWidth(), original.getHeight()));
		filter.addObserver(observer);

		setTitle(new StringBuilder(app.getFilename()).append(" (").append(app.getNumberOfIterations()).append(" iterations, ")
				.append(app.getNumberOfSamples()).append(" samples)").toString());

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);

		setLayout(new BorderLayout());
		addControlPane();

		addPreview();
		addSnapshotPane();
		processor.execute();
		pack();
	}

	/**
	 * Creates the panel at the top of the window containing all the controls.
	 */
	private void addControlPane() {
		controls.setLayout(new BorderLayout());
		upperControls.setLayout(new BoxLayout(upperControls, BoxLayout.X_AXIS));

		addUpdateCheckbox();

		upperControls.add(Box.createHorizontalGlue());

		addSaveButton();
		controls.add(upperControls, BorderLayout.NORTH);
		controls.add(new JSeparator(), BorderLayout.SOUTH);
		add(controls, BorderLayout.NORTH);
	}

	/**
	 * Creates the panel at the top of the window containing all the controls.
	 */
	private void addSnapshotPane() {
		snapControl.setLayout(new BoxLayout(snapControl, BoxLayout.X_AXIS));

		addSnapshotSlider();
		add(snapControl, BorderLayout.SOUTH);
	}

	/**
	 * Creates a checkbox that allows the user disable continuous updates.
	 */
	private void addUpdateCheckbox() {
		JCheckBox checkbox = new JCheckBox("Continuous Updates");
		checkbox.setSelected(true);

		checkbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (checkbox.isSelected()) {
					filter.addObserver(observer);
					preview.setVisible(true);
				} else {
					filter.removeObserver(observer);
					if (!processor.isDone()) {
						preview.setVisible(false);
					}
				}
			}
		});

		upperControls.add(checkbox);
	}

	/**
	 * Creates a slider that allows the user to select a specific snapshot.
	 */
	private void addSnapshotSlider() {
		JLabel label = new JLabel("Snapshot(" + app.getNumberOfIterations() + ")");
		slider.setMinimum(1);
		slider.setMaximum(app.getNumberOfIterations());
		slider.setValue(app.getNumberOfIterations());
		slider.setMajorTickSpacing(app.getNumberOfIterations());
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setEnabled(false);

		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (!slider.getValueIsAdjusting()) {
					label.setText("Snapshot(" + slider.getValue() + ")");
					output = filter.getSnapshot(mementos.get(slider.getValue() - 1));
					preview.setIcon(new ImageIcon(output));
				}
			}
		});

		snapControl.add(slider);
		snapControl.add(label);
	}

	/**
	 * Creates a button that opens a dialog where the user can save the
	 * resulting image.
	 */
	private void addSaveButton() {
		saveButton.setEnabled(false);

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG image", "png"));
				fileChooser.setAcceptAllFileFilterUsed(false);

				if (fileChooser.showSaveDialog(saveButton) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					if (file != null) {
						String extension = FilenameUtils.getExtension(file.getName());
						if (!extension.equalsIgnoreCase("png")) {
							JOptionPane.showMessageDialog(RunWindow.this, "Image format not supported.", "Image format error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								ImageIO.write(output, extension, file);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		});

		upperControls.add(saveButton);
	}

	/**
	 * Creates the preview area and starts generation of the image.
	 */
	private void addPreview() {
		BufferedImage original = app.getOriginalImage();
		preview.setPreferredSize(new Dimension(original.getWidth(), original.getHeight()));
		add(preview, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (!processor.isDone()) {
					processor.cancel(true);
				}
			}
		});
	}
}
