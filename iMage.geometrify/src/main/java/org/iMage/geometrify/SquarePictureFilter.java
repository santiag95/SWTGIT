package org.iMage.geometrify;

import java.awt.Point;


public class SquarePictureFilter extends GeneralPictureFilter {

	
	public SquarePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	


	@Override
	protected IPrimitive generatePrimitive() {
		// TODO Auto-generated method stub
		
		Point centerPoint = pointGenerator.nextPoint();
		Point pointToLength = pointGenerator.nextPoint();
		
		int length = calculateDistance(centerPoint, pointToLength);
		
		
		return new Square(centerPoint, length);
	}
	

}
