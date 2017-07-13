package org.iMage.geometrify;

import org.kohsuke.MetaInfServices;

/**
 * The "Geometrify" filter for ellipses.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
@MetaInfServices(PictureFilter.class)
public class EllipsePictureFilter extends PictureFilter {
	/**
	 * Creates a new picture filter for ellipses.
	 * 
	 * @param pointGenerator
	 *            the source to be used for generating points
	 */
	public EllipsePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}

	/**
	 * Creates a new picture filter for ellipses.
	 */
	public EllipsePictureFilter() {
	}
	
	@Override
	protected IPrimitive generatePrimitive() {
		return new Ellipse(pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}
}
