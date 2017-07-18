package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A template class providing an implementation of the "Geometrify" filter for
 * different primitives.
 * 
 * @author Tobias Hey, Dominic Ziegler
 *
 */
public abstract class PictureFilter extends AbstractPrimitivePictureFilter {
	private static final int HEX_FF = 0xFF;

	private List<PictureFilterObserver> observers = new LinkedList<>();
	private List<Memento> mementos;

	/**
	 * Constructor for a new picture filter with the given point source.
	 * 
	 * @param pointGenerator
	 *            the source to be used for generating points
	 */
	public PictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
		mementos = new ArrayList<>();
	}

	/**
	 * Default constructor for a new picture filter.
	 */
	public PictureFilter() {
		mementos = new ArrayList<>();
	}

	/**
	 * Gets the mementos for the last run of the
	 * {@link #apply(BufferedImage, int, int) apply} method.
	 * 
	 * @return the mementos
	 */
	public List<Memento> getMementos() {
		return mementos;
	}

	/**
	 * Generates a snapshot of the given iteration of the filter run specified
	 * by a {@link Memento} object.
	 * 
	 * @see #getMemento()
	 * 
	 * @param memento
	 *            the memento representing a filter run
	 * @param iteration
	 *            the desired iteration (from 0 to the number of iterations in
	 *            the specified filter run)
	 * @return the snapshot image
	 * @throws IndexOutOfBoundsException
	 *             if the specified iteration does not exist in the given
	 *             memento
	 */
	public BufferedImage getSnapshot(Memento memento) {

		BufferedImage result = new BufferedImage(memento.width, memento.height, memento.type);
		Memento previous = memento;
		List<IPrimitive> primitives = new ArrayList<>();
		do {
			primitives.add(previous.latestPrimitive);
			previous = previous.predecessor;
		} while (previous != null);
		for (int i = primitives.size() - 1; i >= 0; i--) {
			addToImage(result, primitives.get(i));
		}
		return result;
	}

	/**
	 * Represents a run of the filter, allowing to access snapshots of a
	 * specific iteration using {@link PictureFilter#getSnapshot(Memento, int)}.
	 * 
	 * @see PictureFilter#getMemento()
	 */
	public final class Memento {
		private IPrimitive latestPrimitive;
		private Memento predecessor;
		private int width;
		private int height;
		private int type;

		private Memento(IPrimitive latestPrimitive, int width, int height, int type, Memento predecessor) {
			this.latestPrimitive = latestPrimitive;
			this.width = width;
			this.height = height;
			this.type = type;
			this.predecessor = predecessor;
		}
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

	private void iterationCompleted(BufferedImage image) {
		observers.forEach((observer) -> observer.update(image));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.iMage.geometrify.AbstractPrimitivePictureFilter#calculateColor(java.
	 * awt.image.BufferedImage, org.iMage.geometrify.IPrimitive)
	 */
	@Override
	protected Color calculateColor(BufferedImage original, IPrimitive primitive) {
		final int bandwidth = (original.getColorModel().hasAlpha()) ? 4 : 3;

		final int width = original.getWidth();
		final int height = original.getHeight();

		final Point start = primitive.getBoundingBox().getUpperLeftCorner();
		final Point end = primitive.getBoundingBox().getLowerRightCorner();

		int numPixels = 0;
		int red = 0;
		int green = 0;
		int blue = 0;
		int alpha = 0;

		final int wStart = Math.max(0, start.x);
		final int wEnd = Math.min(width - 1, end.x);
		final int hStart = Math.max(0, start.y);
		final int hEnd = Math.min(height - 1, end.y);

		final int rectangleWidth = wEnd - wStart + 1;
		final int rectangleHeight = hEnd - hStart + 1;

		int[] imgPixels = new int[rectangleWidth * rectangleHeight * bandwidth];
		original.getRaster().getPixels(wStart, hStart, rectangleWidth, rectangleHeight, imgPixels);

		Point currPixel = new Point();

		for (int y = 0; y < rectangleHeight; y++) {
			for (int x = 0; x < rectangleWidth; x++) {
				currPixel.setLocation(x + wStart, y + hStart);
				if (primitive.isInsidePrimitive(currPixel)) {
					int pixelIndex = (y * rectangleWidth + x) * bandwidth;
					numPixels++;

					if (bandwidth == 4) {
						alpha += imgPixels[pixelIndex + 3];
					}
					red += imgPixels[pixelIndex + 2];
					green += imgPixels[pixelIndex + 1];
					blue += imgPixels[pixelIndex];
				}
			}
		}
		// normalize color values
		if (numPixels > 0) {
			alpha = alpha / numPixels;
			red = red / numPixels;
			green = green / numPixels;
			blue = blue / numPixels;
		}
		if (original.getColorModel().hasAlpha()) {
			return new Color(red, green, blue, alpha);
		} else {
			return new Color(red, green, blue);
		}
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
		mementos.clear();
		int width = image.getWidth();
		int height = image.getHeight();

		// construct "empty" image
		BufferedImage result = new BufferedImage(width, height, image.getType());

		Memento previousMemento = null;

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

			Memento memento = new Memento(bestPrimitive, width, height, image.getType(), previousMemento);
			mementos.add(memento);
			previousMemento = memento;

			iterationCompleted(result);
			if (Thread.currentThread().isInterrupted()) {
				return result;
			}

		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.iMage.geometrify.AbstractPrimitivePictureFilter#calculateDifference(
	 * java.awt.image.BufferedImage, java.awt.image.BufferedImage,
	 * org.iMage.geometrify.IPrimitive)
	 */
	@Override
	protected int calculateDifference(BufferedImage original, BufferedImage current, IPrimitive primitive) {
		assert (primitive.getColor() != null) : "Average color of primitive has not been calculated, yet";

		final int bandwidth = (original.getColorModel().hasAlpha()) ? 4 : 3;

		final int width = original.getWidth();
		final int height = original.getHeight();

		int[] orgPixels = new int[width * height * bandwidth];
		int[] currPixels = new int[width * height * bandwidth];

		current.getRaster().getPixels(0, 0, width, height, currPixels);
		original.getRaster().getPixels(0, 0, width, height, orgPixels);

		Point currPixel = new Point();

		int difference = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixelIndex = (y * width + x) * bandwidth;
				currPixel.setLocation(x, y);

				// inside Primitive use calculated avg between current and
				// primitive color as value to calculate difference with
				if (primitive.isInsidePrimitive(currPixel)) {
					for (int n = 0; n < bandwidth; n++) {
						difference += Math.abs(orgPixels[pixelIndex + n]
								- ((currPixels[pixelIndex + n] + ((primitive.getColor().getRGB() >> n * 8) & HEX_FF)) / 2));
					}
				} else {
					for (int n = 0; n < bandwidth; n++) {
						difference += Math.abs(orgPixels[pixelIndex + n] - currPixels[pixelIndex + n]);
					}
				}
			}
		}
		return difference;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.iMage.geometrify.AbstractPrimitivePictureFilter#addToImage(java.awt.
	 * image.BufferedImage, org.iMage.geometrify.IPrimitive)
	 */
	@Override
	protected void addToImage(BufferedImage current, IPrimitive primitive) {
		assert (primitive.getColor() != null) : "Average color of primitive has not been calculated, yet";

		final int bandwidth = (current.getColorModel().hasAlpha()) ? 4 : 3;

		final int width = current.getWidth();
		final int height = current.getHeight();

		final Point start = primitive.getBoundingBox().getUpperLeftCorner();
		final Point end = primitive.getBoundingBox().getLowerRightCorner();

		final int wStart = Math.max(0, start.x);
		final int wEnd = Math.min(width - 1, end.x);
		final int hStart = Math.max(0, start.y);
		final int hEnd = Math.min(height - 1, end.y);

		int[] imgPixels = new int[width * height * bandwidth];

		final int rectangleWidth = wEnd - wStart + 1;
		final int rectangleHeight = hEnd - hStart + 1;

		current.getRaster().getPixels(wStart, hStart, rectangleWidth, rectangleHeight, imgPixels);

		Point currPixel = new Point();
		for (int y = 0; y < rectangleHeight; y++) {
			for (int x = 0; x < rectangleWidth; x++) {
				currPixel.setLocation(x + wStart, y + hStart);

				if (primitive.isInsidePrimitive(currPixel)) {
					int pixelIndex = (y * rectangleWidth + x) * bandwidth;

					for (int n = 0; n < bandwidth; n++) {
						imgPixels[pixelIndex + n] = Math.max(0,
								Math.min(255, (((primitive.getColor().getRGB() >> n * 8) & HEX_FF) + imgPixels[pixelIndex + n]) / 2));
					}
				}
			}
		}
		current.getRaster().setPixels(wStart, hStart, rectangleWidth, rectangleHeight, imgPixels);
	}

}
