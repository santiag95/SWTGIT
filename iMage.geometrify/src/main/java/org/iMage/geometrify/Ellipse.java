package org.iMage.geometrify;

import java.awt.Point;

/**
 * An ellipse.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public class Ellipse extends AbstractPrimitive {
	private Point p;
	private int width;
	private int height;
	
	/**
	 * Creates a new ellipse.
	 * 
	 * @param p
	 *            the center of the ellipse
	 * @param q
	 *            a point determining the horizontal (x-coordinate) and 
	 *            vertical (y-coordinate) radius of the ellipse
	 */
	public Ellipse(Point p, Point q) {		
		this.p = p;
		this.width = Math.abs(q.x - p.x);
		this.height = Math.abs(q.y - p.y);
		
		setBoundingBox(new BoundingBox(new Point(p.x - width, p.y - height), 
		                               new Point(p.x + width, p.y + height)));
	}

	@Override
	public boolean isInsidePrimitive(Point point) {
		double dx = point.x - p.x;
		double dy = point.y - p.y;		
		return (dx * dx / (width * width)) + (dy * dy / (height * height)) <= 1.0;
	}
}
