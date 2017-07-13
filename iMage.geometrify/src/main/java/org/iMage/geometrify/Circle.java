package org.iMage.geometrify;

import java.awt.Point;

/**
 * A circle.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public class Circle extends Ellipse {
	/**
	 * Creates a new circle.
	 * 
	 * @param p
	 *            the center of the circle
	 * @param q
	 *            a point whose x-coordinate determines the radius of the circle
	 */
	public Circle(Point p, Point q) {
		super(p, new Point(q.x, q.x));
	}
}
