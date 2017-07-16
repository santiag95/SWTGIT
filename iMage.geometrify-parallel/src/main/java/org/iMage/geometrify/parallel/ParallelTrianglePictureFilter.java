package org.iMage.geometrify.parallel;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;
import org.iMage.geometrify.NonRandomPointGenerator;
import org.iMage.geometrify.PictureFilter;
import org.iMage.geometrify.Triangle;

public class ParallelTrianglePictureFilter extends ParallelFilter{

	public ParallelTrianglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	
    public ParallelTrianglePictureFilter() {
    	super();
	}
	
	@Override
	protected IPrimitive generatePrimitive() {
		return new Triangle(pointGenerator.nextPoint(), pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}

}
