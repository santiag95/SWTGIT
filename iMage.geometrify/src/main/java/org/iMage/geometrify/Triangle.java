package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;

/**
 * A triangle.
 *
 * @author Dominic Ziegler, Martin Blersch
 * @version 1.0
 */
public class Triangle implements IPrimitive {
<<<<<<< HEAD
	private BoundingBox boundingBox;
	private Color color;
	//	Alternative Implementierung:
	//	private Polygon polygon;
	protected Point a, b, c;
=======
	/**
	 * The Color of the triangle.
	 */
	Color color = null;
	/**
	 * The first point of the triangle.
	 */
	Point pointA = null;
	/**
	 * The second point of the triangle.
	 */
	Point pointB = null;
	/**
	 * The third point of the triangle.
	 */
	Point pointC = null;

>>>>>>> d3e926f0ce8bc5aa499085a74d34ed4e5a7cdaa7

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
<<<<<<< HEAD
		Point upperLeft = new Point(Math.min(Math.min(a.x, b.x), c.x), Math.min(Math.min(a.y, b.y), c.y));
		Point lowerRight = new Point(Math.max(Math.max(a.x, b.x), c.x), Math.max(Math.max(a.y, b.y), c.y));

		boundingBox = new BoundingBox(upperLeft, lowerRight);
		//		Alternative Implementierung:
		//		polygon = new Polygon();
		//		polygon.addPoint(a.x, a.y);
		//		polygon.addPoint(b.x, b.y);
		//		polygon.addPoint(c.x, c.y);
		this.a = a;
		this.b = b;
		this.c = c;
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

	//	Alternative Implementierung:
	//
	//	@Override
	//	public boolean isInsidePrimitive(Point point) {
	//		return polygon.contains(point.x, point.y);
	//	}

	@Override
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
=======
		pointA = a;
		pointB = b;
		pointC = c;
		
	}

	/**
	 * Proofs if the given point p in the parameter is inside the primitive figure.
	 * @param p the point thats going to be tested
	 * @return true if the point p is inside.
	 *         false if the point p is outside
	 */
	public boolean isInsidePrimitive(Point p) {
		int p1x = pointA.x;
		int p2x = pointB.x;
		int p3x = pointC.x;
		int p1y = pointA.y;
		int p2y = pointB.y;
		int p3y = pointC.y;
		float alpha = ((p2y - p3y) * (p.x - p3x) + (p3x - p2x) * (p.y - p3y)) 
				/ ((p2y - p3y) * (p1x - p3x) + (p3x - p2x) * (p1y - p3y));
		float beta = ((p3y - p1y) * (p.x - p3x) + (p1x - p3x) * (p.y - p3y)) 
				/ ((p2y - p3y) * (p1x - p3x) + (p3x - p2x) * (p1y - p3y));
		float gamma = 1.0f - alpha - beta;
		return (alpha < 0) && (gamma < 0) && (beta < 0);
	}

	/**
	 * Gives the Bounding box generated based on the triangle coordinates.
	 * @return BoundingBox the boundingBox where the primitive figure can be contained.
	 */
	public BoundingBox getBoundingBox() {
		BoundingBox b = new BoundingBox(pointA, pointC);
		return b;
	}

	/**
	 * Gives the color contained in the triangle.
	 * @return Color the color of the triangle.
	 */
	public Color getColor() {
		return color;
		
	}

	/**
	 * Sets a given color to the triangle.
	 * @param c the color to be set in the triangle.
	 */
>>>>>>> d3e926f0ce8bc5aa499085a74d34ed4e5a7cdaa7
	public void setColor(Color c) {
		color = c;
	}
}
