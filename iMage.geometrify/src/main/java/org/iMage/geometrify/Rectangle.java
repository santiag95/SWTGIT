package org.iMage.geometrify;


import java.awt.Point;
/**
 * A rectangle.
 * 
 * @author santiagotafur
 *@version 1.0
 */
public class Rectangle extends GeneralPrimitive {


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
	 * Creates a rectangle.
	 * @param a first point to model the rectangle.
	 * @param b second point to create the rectangle.
	 */
	public Rectangle(Point a, Point b) {
		int xMIN = (int) Math.min(a.getX(), b.getX());
		int xMAX = (int) Math.max(a.getX(), b.getX());
		int yMIN = (int) Math.min(a.getY(), b.getY());
		int yMAX = (int) Math.max(a.getY(), b.getY());
		
		aDown = new Point(xMIN, yMIN);
		aUp = new Point(xMIN, yMAX);
		bDown = new Point(xMAX, yMIN);
		bUp = new Point(xMAX, yMAX);
		
		this.boundingBox = new BoundingBox(aUp, bDown);
		
	}
	
	
	/* (non-Javadoc)
	 * @see org.iMage.geometrify.GeneralPrimitive#isInsidePrimitive(java.awt.Point)
	 */
	@Override
	public boolean isInsidePrimitive(Point arg) {
		// TODO Auto-generated method stub
		boolean betweenX = false;
		boolean betweenY = false;
		
		if (arg.getX() > aDown.getX() && arg.getX() < bDown.getX()) {
			betweenX = true;
		}
		
		if (arg.getY() > aDown.getY() && arg.getY() < bUp.getY()) {
			betweenY = true;
		}
		
		return (betweenX && betweenY);
	}


}
