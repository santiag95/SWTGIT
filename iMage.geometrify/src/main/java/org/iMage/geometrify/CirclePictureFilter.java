package org.iMage.geometrify;


import java.awt.Point;

public class CirclePictureFilter extends GeneralPictureFilter{

	
	public CirclePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	

	@Override
	protected IPrimitive generatePrimitive() {
		// TODO Auto-generated method stub
		
		Point centerPoint = pointGenerator.nextPoint();
		Point pointToLength = pointGenerator.nextPoint();
		
		int length = calculateDistance(centerPoint, pointToLength) * 2;
		
		
		return new Circle(centerPoint, length);
	}
	

}
