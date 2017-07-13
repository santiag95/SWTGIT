package org.iMage.geometrify;

import org.kohsuke.MetaInfServices;

/**
 * The "Geometrify" filter for circles.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
@MetaInfServices(PictureFilter.class)
public class CirclePictureFilter extends PictureFilter {
	/**
	 * Creates a new picture filter for circles.
	 * 
	 * @param pointGenerator
	 *            the source to be used for generating points
	 */
	public CirclePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	
	/**
	 * Creates a new picture filter for circles.
	 */
	public CirclePictureFilter() {
	}

	@Override
	protected IPrimitive generatePrimitive() {
		return new Circle(pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}
}
