package org.iMage.geometrify;

import java.awt.Point;

/**
* The {@link NonAxisAllignedRectanglePictureFilter} is a {@link IPrimitiveFilter} which is able
* to reconstruct an image through {@link Circle}s.
* @author santiagotafur
*@version 1.0
*/
public class NonAxisAllignedRectanglePictureFilter extends GeneralPictureFilter {

	/**
	 * Constructs a {@link NonAxisAllignedRectanglePictureFilter} with an specified
	 * {@link IPointGenerator}.
	 *
	 * @param pointGenerator
	 *            The {@link IPointGenerator} to use for generating
	 *            {@link Rectangle} {@link Point}s
	 */
	public NonAxisAllignedRectanglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}

	/* (non-Javadoc)
	 * @see org.iMage.geometrify.GeneralPictureFilter#generatePrimitive()
	 */
	@Override
	protected IPrimitive generatePrimitive() {
		Point firstPoint = pointGenerator.nextPoint();
		int width = calculateDistance(firstPoint, pointGenerator.nextPoint());
		int length = calculateDistance(firstPoint, pointGenerator.nextPoint());
		
		return new NonAxisAllignedRectangle(firstPoint, width, length);
	}

}
