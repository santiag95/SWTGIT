package org.iMage.geometrify;

import java.awt.Point;

/**
 * A triangle.
 * 
 * @author Dominic Ziegler, Martin Blersch
 * @version 1.0
 */
public class Triangle extends AbstractPrimitive {
	private Point a, b, c;

	/**
	 * Creates a new triangle from the given vertices.
	 * 
	 * @param a
	 *            the first vertex
	 * @param b
	 *            the second vertex
	 * @param c
	 *            the third vertex
	 */
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;

		Point upperLeft = new Point(Math.min(Math.min(a.x, b.x), c.x), Math.min(Math.min(a.y, b.y), c.y));
		Point lowerRight = new Point(Math.max(Math.max(a.x, b.x), c.x), Math.max(Math.max(a.y, b.y), c.y));

		setBoundingBox(new BoundingBox(upperLeft, lowerRight));
	}

	private static long crossProduct(Point point, Point trianglePointA, Point trianglePointB) {
		int ax = point.x - trianglePointA.x;
		int ay = point.y - trianglePointA.y;
		int bx = trianglePointA.x - trianglePointB.x;
		int by = trianglePointA.y - trianglePointB.y;

		return ax * by - ay * bx;
	}

	@Override
	public boolean isInsidePrimitive(Point point) {
		boolean b1, b2, b3;

		b1 = crossProduct(point, a, b) > 0;
		b2 = crossProduct(point, b, c) > 0;
		b3 = crossProduct(point, c, a) > 0;

		return (b1 == b2) && (b2 == b3);
	}
}
