package org.iMage.geometrify;

import org.kohsuke.MetaInfServices;

/**
 * The "Geometrify" filter for non axis aligned rectangles.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
@MetaInfServices(PictureFilter.class)
public class NonAxisAlignedRectanglePictureFilter extends PictureFilter {
	/**
	 * Creates a new picture filter for non axis aligned rectangles.
	 * 
	 * @param pointGenerator
	 *            the source to be used for generating points
	 */
	public NonAxisAlignedRectanglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}

	/**
	 * Creates a new picture filter for non axis aligned rectangles.
	 */
	public NonAxisAlignedRectanglePictureFilter() {
	}
	
	@Override
	protected IPrimitive generatePrimitive() {
		return new NonAxisAlignedRectangle(pointGenerator.nextPoint(), pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}
}
