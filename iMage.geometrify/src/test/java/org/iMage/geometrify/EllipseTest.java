package org.iMage.geometrify;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

/**
 * Test class for {@link Ellipse}.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public class EllipseTest extends AbstractPrimitiveTest {
	@Override
	public void setUp() {
		primitive = new Ellipse(new Point(2, 3), new Point(3, 5));
	}

	/**
	 * Test method for {@link Ellipse#isInsidePrimitive(Point)}.
	 */
	@Test
	public void testIsInsidePrimitive() {
		assertInside(1, 3);
		for (int y = 1; y <= 5; y++) {
			assertInside(2, y);
		}
		assertInside(3, 3);
		
		// Test some points inside bounding box
		assertOutside(1, 1);
		assertOutside(1, 2);
		assertOutside(3, 4);
		assertOutside(3, 5);
		
		// Test some points outside bounding box
		assertOutside(0, 0);
		assertOutside(0, 2);
		assertOutside(1, 0);
	}
	
	/**
	 * Test method for {@link Ellipse#getBoundingBox()}.
	 */
	@Test
	public void testGetBoundingBox() {
		BoundingBox bbox = primitive.getBoundingBox();
		assertEquals(new Point(1, 1), bbox.getUpperLeftCorner());
		assertEquals(new Point(3, 5), bbox.getLowerRightCorner());
	}
}
