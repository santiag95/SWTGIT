package org.iMage.geometrify.parallel;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;
import org.iMage.geometrify.Square;

public class ParallelSquarePictureFilter extends ParallelFilter{

	public ParallelSquarePictureFilter(IPointGenerator pointGenerator, int cores) {
		super(pointGenerator, cores);
	}
	
	public ParallelSquarePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	
    public ParallelSquarePictureFilter() {
    	super();
	}
    
    @Override
	protected IPrimitive generatePrimitive() {
		return new Square(pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}
}
