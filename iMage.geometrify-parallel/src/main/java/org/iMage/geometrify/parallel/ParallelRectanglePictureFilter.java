package org.iMage.geometrify.parallel;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;
import org.iMage.geometrify.Rectangle;

public class ParallelRectanglePictureFilter extends ParallelFilter{

	public ParallelRectanglePictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator, cores);
	}
	
	public ParallelRectanglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	
    public ParallelRectanglePictureFilter() {
    	super();
	}
    @Override
	protected IPrimitive generatePrimitive() {
		return new Rectangle(pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}
}
