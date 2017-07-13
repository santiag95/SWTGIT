package org.iMage.geometrify;

import org.kohsuke.MetaInfServices;

/**
 * The "Geometrify" filter for squares.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
@MetaInfServices(PictureFilter.class)
public class SquarePictureFilter extends PictureFilter {
	/**
	 * Creates a new picture filter for squares.
	 * 
	 * @param pointGenerator
	 *            the source to be used for generating points
	 */
	public SquarePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}

	/**
	 * Creates a new picture filter for squares.
	 */
	public SquarePictureFilter() {
	}
	
	@Override
	protected IPrimitive generatePrimitive() {
		return new Square(pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}
}
