package org.iMage.geometrify;

import java.awt.Point;

/**
 * The {@link CirclePictureFilter} is a {@link IPrimitiveFilter} which is able
 * to reconstruct an image through {@link Circle}s.
 * @author santiagotafur
 *@version 1.0
 */
public class EllipsePictureFilter extends GeneralPictureFilter {

	/**
	 * Constructs a {@link EllipsePictureFilter} with an specified
	 * {@link IPointGenerator}.
	 *
	 * @param pointGenerator
	 *            The {@link IPointGenerator} to use for generating
	 *            {@link Ellipse} {@link Point}s
	 */
	public EllipsePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	
	


	/* (non-Javadoc)
	 * @see org.iMage.geometrify.GeneralPictureFilter#generatePrimitive()
	 */
	@Override
	protected IPrimitive generatePrimitive() {
		// TODO Auto-generated method stub
		Point centerPoint = pointGenerator.nextPoint();
		Point pointToWidthRadius = pointGenerator.nextPoint();
		Point pointToHeightRadius = pointGenerator.nextPoint();
		int width = calculateDistance(centerPoint, pointToWidthRadius) * 2;
		int height = calculateDistance(centerPoint, pointToHeightRadius) * 2;
		
		return new Ellipse(centerPoint, width, height);
	}
	

}
