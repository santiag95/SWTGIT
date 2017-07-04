package org.iMage.geometrify;


import java.awt.Point;

/**
 * The {@link CirclePictureFilter} is a {@link IPrimitiveFilter} which is able
 * to reconstruct an image through {@link Circle}s.
 * @author santiagotafur
 *@version 1.0
 */
public class CirclePictureFilter extends GeneralPictureFilter {

	
	/**
	 * Constructs a {@link CirclePictureFilter} with an specified
	 * {@link IPointGenerator}.
	 *
	 * @param pointGenerator
	 *            The {@link IPointGenerator} to use for generating
	 *            {@link Circle} {@link Point}s
	 */
	public CirclePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	

	/* (non-Javadoc)
	 * @see org.iMage.geometrify.GeneralPictureFilter#generatePrimitive()
	 */
	@Override
	protected IPrimitive generatePrimitive() {
		// TODO Auto-generated method stub
		
		Point centerPoint = pointGenerator.nextPoint();
		Point pointToLength = pointGenerator.nextPoint();
		
		int length = calculateDistance(centerPoint, pointToLength) * 2;
		
		
		return new Circle(centerPoint, length);
	}
	

}
