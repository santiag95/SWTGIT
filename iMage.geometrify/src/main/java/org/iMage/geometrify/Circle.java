package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;

public class Circle implements IPrimitive {

	protected Point aDown;
	protected Point aUp;
	protected Point bDown;
	protected Point bUp;
	protected Point center;
	
	private int radius;
	private int diameter;
	private BoundingBox boundingBox;
	private Color color;
	
	
	public Circle(Point p, int diameter) {
		this.diameter = diameter;
		this.radius = this.diameter/2;
		this.center = p;
		aDown = new Point(p.x - radius, p.y - radius);
		aUp = new Point(p.x - radius, p.y + radius);
		bDown = new Point(p.x + radius, p.y - radius);
		bUp = new Point(p.x + radius, p.y + radius);
		
		this.boundingBox = new BoundingBox(aUp, bDown);
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
		int distanceToPoint = calculateDistance(center, arg0);
		return distanceToPoint < radius;
	}
	
	public int calculateDistance(Point a, Point b) {
		double distance = Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
		return (int)distance;
	}

	@Override
	public void setColor(Color arg0) {
		// TODO Auto-generated method stub
		this.color = arg0;
		
	}

}
