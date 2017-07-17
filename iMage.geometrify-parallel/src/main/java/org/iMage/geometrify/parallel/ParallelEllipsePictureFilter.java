package org.iMage.geometrify.parallel;

import org.iMage.geometrify.Ellipse;
import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;

public class ParallelEllipsePictureFilter extends ParallelFilter{

	public ParallelEllipsePictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator, cores);
	}
	
	public ParallelEllipsePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	
    public ParallelEllipsePictureFilter() {
    	super();
	}
    @Override
	protected IPrimitive generatePrimitive() {
		return new Ellipse(pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}
}
