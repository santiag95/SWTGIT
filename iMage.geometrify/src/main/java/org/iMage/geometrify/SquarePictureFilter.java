package org.iMage.geometrify;

import java.awt.Point;


/**
 * The {@link SquarePictureFilter} is a {@link IPrimitiveFilter} which is able
 * to reconstruct an image through {@link Square}s.
 * @author santiagotafur
 *@version 1.0
 */
public class SquarePictureFilter extends GeneralPictureFilter {

	
	/**
	 * Constructs a {@link SquarePictureFilter} with an specified
	 * {@link IPointGenerator}.
	 *
	 * @param pointGenerator
	 *            The {@link IPointGenerator} to use for generating
	 *            {@link Square} {@link Point}s
	 */
	public SquarePictureFilter(IPointGenerator pointGenerator) {
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
		
		int length = calculateDistance(centerPoint, pointToLength);
		
		
		return new Square(centerPoint, length);
	}
	

}
