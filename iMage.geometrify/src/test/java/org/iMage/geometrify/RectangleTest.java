package org.iMage.geometrify;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

/**
 * Test class for {@link Rectangle}.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public class RectangleTest extends AbstractPrimitiveTest {
	@Override
	public void setUp() {
		primitive = new Rectangle(new Point(1, 3), new Point(3, 1));
	}

	/**
	 * Test method for {@link Rectangle#isInsidePrimitive(Point)}.
	 */
	@Test
	public void testIsInsidePrimitive() {
		assertInside(2, 2);
		assertInside(3, 3);
		assertOutside(0, 0);
		assertOutside(4, 0);
	}

	/**
	 * Test method for {@link Rectangle#getBoundingBox()}.
	 */
	@Test
	public void testGetBoundingBox() {
		BoundingBox bbox = primitive.getBoundingBox();
		assertEquals(new Point(1, 1), bbox.getUpperLeftCorner());
		assertEquals(new Point(3, 3), bbox.getLowerRightCorner());
	}
}
