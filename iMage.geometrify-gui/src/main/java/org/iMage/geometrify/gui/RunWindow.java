package org.iMage.geometrify.gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;
import org.iMage.geometrify.IPointGenerator;
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
	private JLabel preview = new JLabel();
	private JButton saveButton = new JButton("Save");

	private BufferedImage output;

	private ObservableTrianglePictureFilter filter;

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
			} catch (InterruptedException | ExecutionException e) {
				// ignore
			}
			preview.setIcon(new ImageIcon(output));
			preview.setVisible(true);
			saveButton.setEnabled(true);
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

		BufferedImage original = app.getOriginalImage();
		IPointGenerator pointGenerator = new RandomPointGenerator(original.getWidth(), original.getHeight());
		filter = new ObservableTrianglePictureFilter(pointGenerator);
		filter.addObserver(observer);

		setTitle(app.getFilename() + " (" + app.getNumberOfIterations() + " iterations, " + app.getNumberOfSamples() + " samples)");

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);

		setLayout(new BorderLayout());
		addControlPane();

		addPreview();
		processor.execute();
		pack();
	}

	/**
	 * Creates the panel at the top of the window containing the controls.
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
	 * Creates a checkbox that allows the user to disable continuous updates.
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
				}
			}
		});

		upperControls.add(checkbox);
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
	 * Creates the preview area
	 */
	private void addPreview() {
		BufferedImage original = app.getOriginalImage();
		preview.setPreferredSize(new Dimension(original.getWidth(), original.getHeight()));
		add(preview, BorderLayout.CENTER);

	}

}
