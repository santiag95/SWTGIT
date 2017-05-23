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
	
	Color color = null;
	Point pointA = null;
	Point pointB = null;
	Point pointC = null;


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
		pointA = a;
		pointB = b;
		pointC = c;
		
	}

	@Override
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

	@Override
	public BoundingBox getBoundingBox() {
		BoundingBox b = new BoundingBox(pointA, pointC);
		return b;
	}

	@Override
	public Color getColor() {
		return color;
		
	}

	@Override
	public void setColor(Color c) {
		color = c;
	}
}
