package org.iMage.geometrify;

import java.awt.Point;

public class EllipsePictureFilter extends GeneralPictureFilter{

	public EllipsePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	
	


	@Override
	protected IPrimitive generatePrimitive() {
		// TODO Auto-generated method stub
		Point centerPoint = pointGenerator.nextPoint();
		Point pointToWidthRadius = pointGenerator.nextPoint();
		Point pointToHeightRadius = pointGenerator.nextPoint();
		int width = calculateDistance(centerPoint, pointToWidthRadius) * 2;
		int height = calculateDistance(centerPoint, pointToHeightRadius) * 2;
		
		return new Ellipse(centerPoint, width, height);
	}
	

}
