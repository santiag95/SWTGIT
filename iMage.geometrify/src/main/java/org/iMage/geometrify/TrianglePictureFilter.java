package org.iMage.geometrify;

import java.awt.Color;
import java.awt.image.BufferedImage;
/**
 * The Filter that edits the given photo with the primitive figures.
 *
 * @author Santiago Tafur
 * @version 1.0
 */
public class TrianglePictureFilter extends AbstractPrimitivePictureFilter {
	
	/**
	 * Constructs the triangle filter based on where the pointGenerator was placed.
	 *
	 * @param pointGenerator
	 *            the generator of the point
	 */
	public TrianglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}

	@Override
	protected Color calculateColor(BufferedImage image, IPrimitive primitive) {
		/*
		 * YOUR SOLUTION HERE
		 */
		return null;
	}

	@Override
	public BufferedImage apply(BufferedImage image, int numberOfIterations, int numberOfSamples) {
		/*
		 * YOUR SOLUTION HERE
		 */
		return null;
	}

	@Override
	protected IPrimitive generatePrimitive() {
		/*
		 * YOUR SOLUTION HERE
		 */
		return null;
	}

	@Override
	protected int calculateDifference(BufferedImage original, BufferedImage current, IPrimitive primitive) {
		/*
		 * YOUR SOLUTION HERE
		 */
		return 0;
	}

	@Override
	protected void addToImage(BufferedImage image, IPrimitive primitive) {
		/*
		 * YOUR SOLUTION HERE
		 */
	}
}
