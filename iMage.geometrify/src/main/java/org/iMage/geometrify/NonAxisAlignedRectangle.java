package org.iMage.geometrify;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A non axis-aligned rectangle.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public class NonAxisAlignedRectangle extends AbstractPrimitive {
	private Point a, b, c;
	
	/**
	 * Creates a new rectangle from the given points.
	 * 
	 * @param a
	 *          the first vertex
	 * @param b
	 *          the second vertex
	 * @param p
	 *          a point on the line through the other two vertices
	 */
	public NonAxisAlignedRectangle(Point a, Point b, Point p) {
		this.a = a;
		this.b = b;
		this.c = new Point();
		
		// Project P onto the normal of line AB through point B
		double nx = -(b.y - a.y);
		double ny = b.x - a.x;		
		double lambda = ((p.x - b.x) * nx + (p.y - b.y) * ny) / (nx * nx + ny * ny);
		c.setLocation(b.x + lambda * nx, b.y + lambda * ny);
		
		Point d = new Point(a.x + (c.x - b.x), a.y + (c.y - b.y));
		List<Integer> xs = Arrays.asList(a.x, b.x, c.x, d.x);
		List<Integer> ys = Arrays.asList(a.y, b.y, c.y, d.y);
		Point upperLeft  = new Point(Collections.min(xs), Collections.min(ys));
		Point lowerRight = new Point(Collections.max(xs), Collections.max(ys));
		setBoundingBox(new BoundingBox(upperLeft, lowerRight));
	}

	/*
	 * Calculates PA * PB, i.e. the dot product of the vectors from P to A 
	 * and P to B respectively.
	 */
	private static int dotProduct(Point p, Point a, Point b) {
		return (a.x - p.x) * (b.x - p.x) + (a.y - p.y) * (b.y - p.y);
	}
	
	@Override
	public boolean isInsidePrimitive(Point point) {
		int dot1 = dotProduct(a, b, point);
		int dot2 = dotProduct(b, c, point);

		return 0 <= dot1 && dot1 <= dotProduct(a, b, b)
		    && 0 <= dot2 && dot2 <= dotProduct(b, c, c);
	}
}
