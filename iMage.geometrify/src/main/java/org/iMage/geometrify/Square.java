package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;

public class Square implements IPrimitive {

	private BoundingBox boundingBox;
	private Color color;
	protected Point a,b,c,d;
	
	public Square(Point a, Point b) {
		
	}
	
	
	@Override
	public BoundingBox getBoundingBox() {
		// TODO Auto-generated method stub
		return boundingBox;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public boolean isInsidePrimitive(Point arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		this.color = c;
		
	}

}
