package org.iMage.geometrify;

import org.kohsuke.MetaInfServices;

/**
 * The "Geometrify" filter for rectangles.
 * 
 * @author Tobias Hey
 * @version 1.0
 */
@MetaInfServices(PictureFilter.class)
public class RectanglePictureFilter extends PictureFilter {
	/**
	 * Creates a new picture filter for rectangles.
	 * 
	 * @param pointGenerator
	 *           the source to be used for generating points
	 */
	public RectanglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	
	/**
	 * Creates a new picture filter for rectangles.
	 */
	public RectanglePictureFilter() {
	}

	@Override
	protected IPrimitive generatePrimitive() {
		return new Rectangle(pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}
}
