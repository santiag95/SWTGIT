package org.iMage.geometrify;

import java.awt.Point;

/**
 * A non axis aligned Rectangle.
 * @author santiagotafur
 *@version 1.0
 */
public class NonAxisAllignedRectangle extends GeneralPrimitive {

	/**
	 * the Point most left.
	 */
	protected Point aDown;
	/**
	 * the highest Point.
	 */
	protected Point aUp;
	/**
	 * the lowest Point.
	 */
	protected Point bDown;
	/**
	 * the Point most at right.
	 */
	protected Point bUp;
	
	/**
	 * Models the boundingBox of the primitive.
	 */
	private BoundingBox boundingBox;
	
	/**
	 * Creates a non axis aligned Rectangle.
	 * @param a first Point
	 * @param width to complement the rectangle down diagonal
	 * @param length to complement the rectangle upper diagonal
	 */
	public NonAxisAllignedRectangle(Point a, int width, int length) {
		aDown = a;
		bDown = new Point(aDown.x + width, aDown.y - width);
		aUp = new Point(aDown.x + length, aDown.y + length);
		bUp = new Point(bDown.x + length, bDown.y + length);
		
		Point upperLeftBB = new Point(aDown.x, bDown.y);
		Point lowerRightBB = new Point(bUp.x, aUp.y);
		
		boundingBox = new BoundingBox(upperLeftBB, lowerRightBB);
		
	}
	
	/* (non-Javadoc)
	 * @see org.iMage.geometrify.GeneralPrimitive#isInsidePrimitive(java.awt.Point)
	 */
	@Override
	public boolean isInsidePrimitive(Point a) {
		Point upperLeft = boundingBox.getUpperLeftCorner();
		Point lowerRight = boundingBox.getLowerRightCorner();
		Point upperRight = new Point(lowerRight.x, upperLeft.y);
		Point lowerLeft = new Point(upperLeft.x, lowerRight.y);
		
		
		//checking if the point is in the triangles of the boundingBox outside the rectangle.
		boolean firstTriangle = isInsideTriangle(a, aDown, bDown, lowerLeft);
		boolean secondTriangle = isInsideTriangle(a, aDown, aUp, upperLeft);
		boolean thirdTriangle = isInsideTriangle(a, aUp, bUp, upperRight);
		boolean fourthTriangle = isInsideTriangle(a, bUp, bDown, lowerRight);
		
		return !(firstTriangle || secondTriangle || thirdTriangle || fourthTriangle);
	}
	
	
	
	/**
	 * Checks if a point is inside a Triangle.
	 * @param point to be checked
	 * @param a first point of the Triangle
	 * @param b second Point of the Triangle
	 * @param c third Point of the Triangle
	 * @return true if the point is inside
	 */
	public boolean isInsideTriangle(Point point, Point a, Point b, Point c) {
		boolean b1, b2, b3;

		b1 = crossProduct(point, a, b) > 0;
		b2 = crossProduct(point, b, c) > 0;
		b3 = crossProduct(point, c, a) > 0;
		return (b1 == b2) && (b2 == b3);
	}
	
	private static long crossProduct(Point point, Point trianglePointA, Point trianglePointB) {
		int ax = point.x - trianglePointA.x;
		int ay = point.y - trianglePointA.y;
		int bx = trianglePointA.x - trianglePointB.x;
		int by = trianglePointA.y - trianglePointB.y;
		return ax * by - ay * bx;
	}

}
