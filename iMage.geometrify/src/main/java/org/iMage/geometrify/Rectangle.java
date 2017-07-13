package org.iMage.geometrify;

import java.awt.Point;

/**
 * A rectangle.
 * 
 * @author Tobias Hey, Dominic Ziegler
 * @version 1.0
 */
public class Rectangle extends AbstractPrimitive {
	/**
	 * Creates a new rectangle from the given vertices.
	 * 
	 * @param a
	 *            the first vertex
	 * @param b
	 *            the second vertex
	 */
	public Rectangle(Point a, Point b) {
		Point upperLeft = new Point(Math.min(a.x, b.x), Math.min(a.y, b.y));
		Point lowerRight = new Point(Math.max(a.x, b.x), Math.max(a.y, b.y));
	
		setBoundingBox(new BoundingBox(upperLeft, lowerRight));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.iMage.geometrify.IPrimitive#isInsidePrimitive(java.awt.Point)
	 */
	@Override
	public boolean isInsidePrimitive(Point point) {
		return point.x >= getBoundingBox().getUpperLeftCorner().x
		    && point.x <= getBoundingBox().getLowerRightCorner().x
		    && point.y <= getBoundingBox().getLowerRightCorner().y
		    && point.y >= getBoundingBox().getUpperLeftCorner().y;
	}
}
