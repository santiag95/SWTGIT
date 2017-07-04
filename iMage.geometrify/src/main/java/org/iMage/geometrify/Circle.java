package org.iMage.geometrify;


import java.awt.Point;

/**
 * A Circle.
 * 
 * @author santiagotafur
 *@version 1.0
 */
public class Circle extends GeneralPrimitive {
	/**
	 * Models the boundingBox of the primitive.
	 */
	private BoundingBox boundingBox;
	
	/**
	 * the Point of bottomLeft.
	 */
	protected Point aDown;
	/**
	 * the Point of  upperLeft.
	 */
	protected Point aUp;
	/**
	 * the Point of bottomRight.
	 */
	protected Point bDown;
	/**
	 * the Point of upperRight.
	 */
	protected Point bUp;
	
	/**
	 * the point of the center.
	 */
	protected Point center;
	
	/**
	 * Radius of the circle.
	 */
	private int radius;
	/**
	 * diameter of the circle.
	 */
	private int diameter;
	
	
	
	/**
	 * Creates a circle.
	 * @param p center point to create a circle.
	 * @param diameter diameter of the circle.
	 */
	public Circle(Point p, int diameter) {
		this.diameter = diameter;
		this.radius = this.diameter / 2;
		this.center = p;
		aDown = new Point(p.x - radius, p.y - radius);
		aUp = new Point(p.x - radius, p.y + radius);
		bDown = new Point(p.x + radius, p.y - radius);
		bUp = new Point(p.x + radius, p.y + radius);
		
		this.boundingBox = new BoundingBox(aUp, bDown);
	}
	

	/* (non-Javadoc)
	 * @see org.iMage.geometrify.GeneralPrimitive#isInsidePrimitive(java.awt.Point)
	 */
	@Override
	public boolean isInsidePrimitive(Point arg0) {
		int distanceToPoint = calculateDistance(center, arg0);
		return distanceToPoint < radius;
	}
	
	/**
	 * Calculates the distance between two points.
	 * @param a first point.
	 * @param b second point.
	 * @return distance between the two points.
	 */
	public int calculateDistance(Point a, Point b) {
		double distance = Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
		return (int) distance;
	}

	

}
