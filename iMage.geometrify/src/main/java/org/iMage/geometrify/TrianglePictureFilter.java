package org.iMage.geometrify;


import java.awt.Point;

/**
 * The {@link TrianglePictureFilter} is a {@link IPrimitiveFilter} which is able
 * to reconstruct an image through {@link Triangle}s.
 *
 * @author Tobias Hey
 *
 */
public class TrianglePictureFilter extends GeneralPictureFilter {

	/**
	 * Constructs a {@link TrianglePictureFilter} with an specified
	 * {@link IPointGenerator}.
	 *
	 * @param pointGenerator
	 *            The {@link IPointGenerator} to use for generating
	 *            {@link Triangle} {@link Point}s
	 */
	public TrianglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.iMage.geometrify.AbstractPrimitivePictureFilter#generatePrimitive()
	 */
	@Override
	protected IPrimitive generatePrimitive() {
		return new Triangle(pointGenerator.nextPoint(), pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}
}
