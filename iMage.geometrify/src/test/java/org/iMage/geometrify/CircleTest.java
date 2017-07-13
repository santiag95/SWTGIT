package org.iMage.geometrify;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

/**
 * Test class for {@link Circle}.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public class CircleTest extends AbstractPrimitiveTest {
	@Override
	public void setUp() {
		primitive = new Circle(new Point(2, 2), new Point(3, 42));
	}

	/**
	 * Test method for {@link Circle#isInsidePrimitive(Point)}.
	 */
	@Test
	public void testIsInsidePrimitive() {
		assertInside(1, 2);
		assertInside(2, 1);
		assertInside(2, 2);
		assertInside(2, 3);
		assertInside(3, 2);

		// Test points inside bounding box
		assertOutside(1, 1);
		assertOutside(1, 3);
		assertOutside(3, 1);
		assertOutside(3, 3);

		// Test some points outside bounding box
		assertOutside(0, 0);
		assertOutside(0, 2);
		assertOutside(1, 0);
		assertOutside(2, 4);		
	}
	
	/**
	 * Test method for {@link Circle#getBoundingBox()}.
	 */
	@Test
	public void testGetBoundingBox() {
		BoundingBox bbox = primitive.getBoundingBox();
		assertEquals(new Point(1, 1), bbox.getUpperLeftCorner());
		assertEquals(new Point(3, 3), bbox.getLowerRightCorner());
	}
}
