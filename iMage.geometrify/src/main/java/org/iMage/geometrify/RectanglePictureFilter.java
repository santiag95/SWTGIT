package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class RectanglePictureFilter extends AbstractPrimitivePictureFilter{

	/**
	 * Constructs a {@link RectanglePictureFilter} with an specified
	 * {@link IPointGenerator}.
	 *
	 * @param pointGenerator
	 *            The {@link IPointGenerator} to use for generating
	 *            {@link Triangle} {@link Point}s
	 */
	public RectanglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	
	@Override
	protected void addToImage(BufferedImage arg0, IPrimitive arg1) {
		// TODO Auto-generated method stub
		TrianglePictureFilter t = new TrianglePictureFilter(pointGenerator);
		t.addToImage(arg0, arg1);
	}

	@Override
	public BufferedImage apply(BufferedImage image, int numberOfIterations, int numberOfSamples) {
		// TODO Auto-generated method stub
		int width = image.getWidth();
		int height = image.getHeight();

		// construct "empty" image
		BufferedImage result = new BufferedImage(width, height, image.getType());

		for (int i = 0; i < numberOfIterations; i++) {
			int bestDifference = Integer.MAX_VALUE;
			IPrimitive bestPrimitive = null;

			for (int s = 0; s < numberOfSamples; s++) {
				IPrimitive sample = generatePrimitive();
				sample.setColor(calculateColor(image, sample));
				int difference = calculateDifference(image, result, sample);

				if (difference <= bestDifference) {
					bestDifference = difference;
					bestPrimitive = sample;
				}
			}

			addToImage(result, bestPrimitive);
		}

		return result;
	}

	@Override
	protected Color calculateColor(BufferedImage arg0, IPrimitive arg1) {
		// TODO Auto-generated method stub
		TrianglePictureFilter t = new TrianglePictureFilter(pointGenerator);
		return t.calculateColor(arg0, arg1);
	}

	@Override
	protected int calculateDifference(BufferedImage arg0, BufferedImage arg1, IPrimitive arg2) {
		// TODO Auto-generated method stub
		TrianglePictureFilter t = new TrianglePictureFilter(pointGenerator);
		return t.calculateDifference(arg0, arg1, arg2);
	}

	@Override
	protected IPrimitive generatePrimitive() {
		// TODO Auto-generated method stub
		return new Rectangle(pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}

}
