package org.iMage.geometrify;

import java.awt.Point;

/**
 * A square.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public class Square extends Rectangle {
	/**
	 * Creates a new square.
	 * 
	 * @param p
	 *            the upper left corner of the square
	 * @param q
	 *            the size (x-coordinate) of the square
	 */
	public Square(Point p, Point q) {
		super(p, new Point(p.x + q.x, p.y + q.x));
	}
}
