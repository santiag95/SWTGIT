package org.iMage.geometrify;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link Triangle}.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public class TriangleTest extends AbstractPrimitiveTest {
	@Override
	public void setUp() {
		Point a = new Point(1, 1);
		Point b = new Point(10, 1);
		Point c = new Point(1, 10);
		primitive = new Triangle(a, b, c);
	}

	/**
	 * Test method for {@link Triangle#isInsidePrimitive(Point)}.
	 */
	@Test
	public void testIsInsidePrimitive() {
		assertInside(5, 5);
		assertOutside(6, 6);
	}

	/**
	 * Test method for {@link Triangle#getBoundingBox()}.
	 */
	@Test
	public void testGetBoundingBox() {
		BoundingBox bbox = primitive.getBoundingBox();
		assertEquals(new Point(1, 1), bbox.getUpperLeftCorner());
		assertEquals(new Point(10, 10), bbox.getLowerRightCorner());
	}
}
