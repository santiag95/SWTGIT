package org.iMage.geometrify.parallel;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;
import org.iMage.geometrify.NonAxisAlignedRectangle;

public class ParallelNonAxisAlignedRectanglePictureFilter extends ParallelFilter{

	public ParallelNonAxisAlignedRectanglePictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator, cores);
	}
	
	public ParallelNonAxisAlignedRectanglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	
    public ParallelNonAxisAlignedRectanglePictureFilter() {
    	super();
	}
	
	@Override
	protected IPrimitive generatePrimitive() {
		return new NonAxisAlignedRectangle(pointGenerator.nextPoint(), pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}
}
