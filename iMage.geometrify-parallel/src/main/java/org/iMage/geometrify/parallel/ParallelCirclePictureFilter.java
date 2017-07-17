package org.iMage.geometrify.parallel;

import org.iMage.geometrify.Circle;
import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;

public class ParallelCirclePictureFilter extends ParallelFilter{

	public ParallelCirclePictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator, cores);
	}
	
	public ParallelCirclePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	
    public ParallelCirclePictureFilter() {
    	super();
	}
	@Override
	protected IPrimitive generatePrimitive() {
		return new Circle(pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}
}
