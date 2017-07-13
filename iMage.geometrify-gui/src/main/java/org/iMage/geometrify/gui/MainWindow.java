package org.iMage.geometrify.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.ListCellRenderer;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.iMage.geometrify.IPrimitiveFilter;
import org.iMage.geometrify.PictureFilter;
import org.iMage.geometrify.RandomPointGenerator;

/**
 * The main window of the GUI.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final String WINDOW_TITLE = "iLlustrate - Geometrify";
	private static final Dimension PREVIEW_SIZE = new Dimension(150, 150);
	private static final int PREVIEW_ITERATIONS = 100;
	private static final int PREVIEW_SAMPLES = 30;
	private static final int MAX_ITERATIONS = 2000;
	private static final int MAX_SAMPLES = 200;

	private GeometrifyGUI app;

	private JLabel previewOriginal = new JLabel();
	private JLabel previewProcessed = new JLabel();
	private JLabel iterLabel = new JLabel();
	private JLabel sampleLabel = new JLabel();
	private JPanel config = new JPanel();
	private JPanel buttonPane = new JPanel();

	private BufferedImage preview;

	/**
	 * Background worker class responsible for generating the preview image.
	 */
	private class PreviewGenerator extends SwingWorker<BufferedImage, Object> {
		IPrimitiveFilter filter;

		public PreviewGenerator(IPrimitiveFilter filter) {
			this.filter = filter;
		}

		@Override
		protected BufferedImage doInBackground() {
			return filter.apply(preview, PREVIEW_ITERATIONS, PREVIEW_SAMPLES);
		}

		@Override
		protected void done() {
			try {
				previewProcessed.setIcon(new ImageIcon(get()));
				previewProcessed.setVisible(true);
			} catch (CancellationException | InterruptedException | ExecutionException e) {
				// ignore
			}
		}
	}

	private PreviewGenerator currentTask;

	/**
	 * Creates a new main window.
	 * 
	 * @param app
	 *            the application the window belongs to
	 */
	public MainWindow(GeometrifyGUI app) {
		super(WINDOW_TITLE);
		this.app = app;

		setSize(400, 400);
		setResizable(false);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		addPreviewArea();
		addConfigPane();
		addButtons();
	}

	private void addPreviewArea() {
		JPanel panel = new JPanel(new GridLayout(0, 2, 5, 0));
		panel.setMaximumSize(new Dimension(2 * PREVIEW_SIZE.width + 5, PREVIEW_SIZE.height));
		panel.setAlignmentX(CENTER_ALIGNMENT);

		previewOriginal.setPreferredSize(PREVIEW_SIZE);
		previewProcessed.setPreferredSize(PREVIEW_SIZE);

		panel.add(previewOriginal);
		panel.add(previewProcessed);

		generatePreview();

		add(panel);
	}

	private void addConfigPane() {
		config.setLayout(new GridBagLayout());

		addShapeSelector();
		addIterationsSlider();
		addSamplesSlider();

		add(config);
	}

	/**
	 * Creates a combo box to select the filter to be applied.
	 */
	private void addShapeSelector() {
		JComboBox<PictureFilter> box = new JComboBox<>(app.getAvailableFilters());
		box.setRenderer(new ListCellRenderer<PictureFilter>() {
			@Override
			public Component getListCellRendererComponent(JList<? extends PictureFilter> list, PictureFilter filter, int index,
					boolean isSelected, boolean cellHasFocus) {
				JLabel label = new JLabel(filter.getClass().getSimpleName());
				label.setOpaque(true);

				if (isSelected) {
					label.setForeground(UIManager.getColor("List.selectionForeground"));
					label.setBackground(UIManager.getColor("List.selectionBackground"));
				} else {
					label.setForeground(UIManager.getColor("List.foreground"));
					label.setBackground(UIManager.getColor("List.background"));
				}

				return label;
			}
		});

		box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				app.setFilter(box.getItemAt(box.getSelectedIndex()));
				updatePreview();
			}
		});

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		config.add(box, constraints);
	}

	/**
	 * Creates a slider to select the number of iterations.
	 */
	private void addIterationsSlider() {
		iterLabel.setText("Iterations(" + app.getNumberOfIterations() + ")");
		JSlider slider = new JSlider(0, MAX_ITERATIONS, app.getNumberOfIterations());

		slider.setMajorTickSpacing(500);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (!slider.getValueIsAdjusting()) {
					app.setNumberOfIterations(slider.getValue());
					iterLabel.setText("Iterations(" + slider.getValue() + ")");
				}
			}
		});

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.LINE_END;
		config.add(iterLabel, constraints);

		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		config.add(slider, constraints);
	}

	/**
	 * Creates a slider to select the number of samples.
	 */
	private void addSamplesSlider() {
		sampleLabel.setText("Samples(" + app.getNumberOfSamples() + ")");
		JSlider slider = new JSlider(0, MAX_SAMPLES, app.getNumberOfSamples());

		slider.setMajorTickSpacing(25);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (!slider.getValueIsAdjusting()) {
					app.setNumberOfSamples(slider.getValue());
					sampleLabel.setText("Samples(" + slider.getValue() + ")");

				}
			}
		});

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.LINE_END;
		config.add(sampleLabel, constraints);

		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		config.add(slider, constraints);
	}

	private void addButtons() {
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));

		addLoadButton();
		buttonPane.add(new Box.Filler(new Dimension(50, 100), new Dimension(50, 100), new Dimension(50, 100)));
		addRunButton();

		add(buttonPane);
	}

	/**
	 * Creates a button that opens a file chooser, allowing the user to load an
	 * image.
	 */
	private void addLoadButton() {
		JButton button = new JButton("Load");

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("Images (*.png)", "png"));
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setMultiSelectionEnabled(false);

				if (fileChooser.showOpenDialog(button) == JFileChooser.APPROVE_OPTION) {
					app.setOriginalImage(fileChooser.getSelectedFile());
					generatePreview();
				}
			}
		});
		button.setToolTipText("Load new picture.");

		buttonPane.add(button);
	}

	/**
	 * Creates a button that opens the {@link RunWindow}.
	 */
	private void addRunButton() {
		JButton button = new JButton("Run");

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				RunWindow window = new RunWindow(app, MainWindow.this);
				window.setVisible(true);
			}
		});
		button.setToolTipText("Run Geometrify");
		buttonPane.add(button);
	}

	/**
	 * Generates the preview image from the original image.
	 */
	private void generatePreview() {
		BufferedImage original = app.getOriginalImage();
		if (original.getWidth() > PREVIEW_SIZE.width || original.getHeight() > PREVIEW_SIZE.height) {
			preview = ImageUtil.resize(original, PREVIEW_SIZE.width, PREVIEW_SIZE.height);
		} else {
			preview = original;
		}
		previewOriginal.setIcon(new ImageIcon(preview));
		updatePreview();
	}

	/**
	 * Updates the preview image after the user has changed the configuration.
	 */
	private void updatePreview() {
		previewProcessed.setVisible(false);

		IPrimitiveFilter filter = app.getFilter();
		filter.setPointGenerator(new RandomPointGenerator(preview.getWidth(), preview.getHeight()));

		// Abort the old task if still running before starting a new one
		if (currentTask != null && !currentTask.isDone()) {
			currentTask.cancel(true);
		}
		currentTask = new PreviewGenerator(filter);
		currentTask.execute();
	}
}
