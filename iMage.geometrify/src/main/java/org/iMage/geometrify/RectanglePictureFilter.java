package org.iMage.geometrify;

import java.awt.Point;

/**
 * The {@link CirclePictureFilter} is a {@link IPrimitiveFilter} which is able
 * to reconstruct an image through {@link Circle}s.
 * @author santiagotafur
 *@version 1.0
 */
public class RectanglePictureFilter extends GeneralPictureFilter {

	
	/**
	 * Constructs a {@link RectanglePictureFilter} with an specified
	 * {@link IPointGenerator}.
	 *
	 * @param pointGenerator
	 *            The {@link IPointGenerator} to use for generating
	 *            {@link Rectangle} {@link Point}s
	 */
	public RectanglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	

	/* (non-Javadoc)
	 * @see org.iMage.geometrify.GeneralPictureFilter#generatePrimitive()
	 */
	@Override
	protected IPrimitive generatePrimitive() {
		// TODO Auto-generated method stub
		return new Rectangle(pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}

}
