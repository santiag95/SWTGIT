package org.iMage.geometrify;

import java.awt.Point;

/**
 * A square.
 * 
 * @author santiagotafur
 *@version 1.0
 */
public class Square extends GeneralPrimitive {

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
	 * Creates a Square.
	 * @param p the start point
	 * @param length the length of the sides
	 */
	public Square(Point p, int length) {
		aDown = p;
		aUp = new Point(p.x, p.y + length);
		bDown = new Point(p.x + length, p.y);
		bUp = new Point(p.x + length, p.y + length);
		
		this.boundingBox = new BoundingBox(aUp, bDown);
	}

	/* (non-Javadoc)
	 * @see org.iMage.geometrify.GeneralPrimitive#isInsidePrimitive(java.awt.Point)
	 */
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
