package org.iMage.geometrify;

import org.kohsuke.MetaInfServices;

/**
 * The "Geometrify" filter for triangles.
 * 
 * @author Tobias Hey
 * @version 1.0
 */
@MetaInfServices(PictureFilter.class)
public class TrianglePictureFilter extends PictureFilter {
	/**
	 * Creates a new picture filter for triangles
	 * 
	 * @param pointGenerator
	 *           the source to be used for generating points
	 */
	public TrianglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}	

	/**
	 * Creates a new picture filter for triangles.
	 */
	public TrianglePictureFilter() {
	}
	
	@Override
	protected IPrimitive generatePrimitive() {
		return new Triangle(pointGenerator.nextPoint(), pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}
}
