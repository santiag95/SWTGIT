package org.iMage.geometrify;


import java.awt.Point;

/**
 * An Ellipse.
 * 
 * @author santiagotafur
 *@version 1.0
 */
public class Ellipse extends GeneralPrimitive {
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
	 * the radius on the x axis.
	 */
	private int xRadius;
	/**
	 * the radius on the y axis. 
	 */
	private int yRadius;
	
	
	/**
	 * Creates an Ellipse.
	 * @param p center point to create it.
	 * @param width of the ellipse.
	 * @param height of the ellipse.
	 */
	public Ellipse(Point p, int width, int height) {
		this.center = p;
		this.xRadius = width / 2;
		this.yRadius = height / 2;
		aDown = new Point(p.x - xRadius, p.y - yRadius);
		aUp = new Point(p.x - xRadius, p.y + yRadius);
		bDown = new Point(p.x + xRadius, p.y - yRadius);
		bUp = new Point(p.x + xRadius, p.y + yRadius);
		
		this.boundingBox = new BoundingBox(aUp, bDown);
	}
	

	/* (non-Javadoc)
	 * @see org.iMage.geometrify.GeneralPrimitive#isInsidePrimitive(java.awt.Point)
	 */
	@Override
	public boolean isInsidePrimitive(Point arg0) {
		 if (xRadius <= 0 || yRadius <= 0) {
	            return false;
		 }
	        Point normalized = new Point(arg0.x - center.x, arg0.y - center.y);

	        boolean total =  ((double) (normalized.x * normalized.y) / (xRadius * xRadius)) 
	        		+ ((double) (normalized.y * normalized.y) / (yRadius * yRadius)) <= 1.0;
	        
	        return total;
	}


}
