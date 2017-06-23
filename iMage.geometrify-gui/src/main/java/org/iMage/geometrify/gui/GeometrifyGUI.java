package org.iMage.geometrify.gui;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This application provides a GUI for the Geometrify image filter.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public final class GeometrifyGUI {
	private static final int IMAGE_MAX_WIDTH = 1024;
	private static final int IMAGE_MAX_HEIGHT = 768;

	public static final int PREVIEW_ITERATIONS = 100;
	public static final int PREVIEW_SAMPLES = 30;

	private int numberOfIterations = 100;
	private int numberOfSamples = 30;
	private BufferedImage original;
	private String filename = "walter.png";

	private JFrame window;

	/*
	 * Private constructor: App is not to be instantiated from outside
	 */
	private GeometrifyGUI() {
	}

	/**
	 * Main method: starts the GUI.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		// Find the system look and feel if present
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException f) {
				// falls back to ugly Metal look and feel
			}
		}

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				GeometrifyGUI app = new GeometrifyGUI();
				app.loadDefaultImage();

				MainWindow window = new MainWindow(app);
				window.setDefaultCloseOperation(MainWindow.EXIT_ON_CLOSE);
				window.setVisible(true);

				app.window = window;
			}
		});
	}

	private void loadDefaultImage() {
		try {
			original = ImageIO.read(getClass().getResourceAsStream(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the currently configured number of iterations.
	 * 
	 * @return the number of iterations
	 */
	public int getNumberOfIterations() {
		return numberOfIterations;
	}

	/**
	 * Sets the number of iterations to the specified value.
	 * 
	 * @param numberOfIterations
	 *            the number of iterations to set
	 */
	public void setNumberOfIterations(int numberOfIterations) {
		this.numberOfIterations = numberOfIterations;
	}

	/**
	 * Gets the currently configured number of samples.
	 * 
	 * @return the number of samples
	 */
	public int getNumberOfSamples() {
		return numberOfSamples;
	}

	/**
	 * Sets the number of samples to the specified value.
	 * 
	 * @param numberOfSamples
	 *            the number of samples to set
	 */
	public void setNumberOfSamples(int numberOfSamples) {
		this.numberOfSamples = numberOfSamples;
	}

	/**
	 * Gets the currently set original image.
	 * 
	 * @return the original image
	 */
	public BufferedImage getOriginalImage() {
		return original;
	}

	/**
	 * Sets the image to be filtered and resizes it if it exceeds the limits.
	 * 
	 * 
	 * @param file
	 *            the image file to set
	 */
	public void setOriginalImage(File file) {
		try {
			BufferedImage image = ImageIO.read(file);
			if (image.getWidth() > IMAGE_MAX_WIDTH || image.getHeight() > IMAGE_MAX_HEIGHT) {
				if (JOptionPane.showConfirmDialog(window, "The image will be resized to " + IMAGE_MAX_WIDTH + "×" + IMAGE_MAX_HEIGHT + ".",
						"Image size", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION) {
					return;
				}
				original = ImageUtil.resize(image, IMAGE_MAX_WIDTH, IMAGE_MAX_HEIGHT);
			} else {
				original = image;
			}
			filename = file.getName();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the file name of the currently set image.
	 * 
	 * @return the file name
	 */
	public String getFilename() {
		return filename;
	}
}
