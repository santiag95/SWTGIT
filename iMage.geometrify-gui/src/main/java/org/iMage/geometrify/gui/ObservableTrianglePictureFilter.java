package org.iMage.geometrify.gui;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;
import org.iMage.geometrify.IPrimitiveFilter;
import org.iMage.geometrify.TrianglePictureFilter;

/**
 * This {@link IPrimitiveFilter} adapts the {@link TrianglePictureFilter} to be
 * able to display snapshots of iterations
 * 
 * @author Tobias Hey, Dominic Ziegler
 *
 */
public class ObservableTrianglePictureFilter extends TrianglePictureFilter {

	private List<PictureFilterObserver> observers = new LinkedList<>();

	/**
	 * Constructs a new instance of a {@link ObservableTrianglePictureFilter}
	 * 
	 * @param pointGenerator
	 *            The {@link IPointGenerator} to be used for generating
	 *            {@link Point}s
	 */
	public ObservableTrianglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}

	/**
	 * Adds an observer to the watchlist.
	 * 
	 * @param observer
	 *            the observer to add
	 */
	public void addObserver(PictureFilterObserver observer) {
		observers.add(observer);
	}

	/**
	 * Removes an observer from the watchlist.
	 * 
	 * @param observer
	 *            the observer to remove
	 * @return {@code true} if the observer was removed from the list, or
	 *         {@code false} if there was no such observer in the list
	 */
	public boolean removeObserver(PictureFilterObserver observer) {
		return observers.remove(observer);
	}

	/**
	 * Informs the registered observers about the specified image
	 * 
	 * @param image
	 *            The {@link BufferedImage} to inform about
	 */
	private void iterationCompleted(BufferedImage image) {
		observers.forEach((observer) -> observer.update(image));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.iMage.geometrify.AbstractPrimitivePictureFilter#apply(java.awt.image.
	 * BufferedImage, int, int)
	 */
	@Override
	public BufferedImage apply(BufferedImage image, int numberOfIterations, int numberOfSamples) {
		int width = image.getWidth();
		int height = image.getHeight();

		// construct "empty" image
		BufferedImage result = new BufferedImage(width, height, image.getType());

		List<IPrimitive> usedPrimitives = new ArrayList<>();
		for (int i = 0; i < numberOfIterations; i++) {
			int bestDistance = Integer.MAX_VALUE;
			IPrimitive bestPrimitive = null;

			for (int s = 0; s < numberOfSamples; s++) {
				IPrimitive sample = generatePrimitive();
				sample.setColor(calculateColor(image, sample));
				int distance = calculateDifference(image, result, sample);

				if (distance <= bestDistance) {
					bestDistance = distance;
					bestPrimitive = sample;
				}
			}

			addToImage(result, bestPrimitive);
			usedPrimitives.add(bestPrimitive);
			iterationCompleted(result);
		}

		return result;
	}

}
