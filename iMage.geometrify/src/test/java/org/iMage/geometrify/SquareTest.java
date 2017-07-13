package org.iMage.geometrify;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

/**
 * Test class for {@link Square}
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public class SquareTest extends AbstractPrimitiveTest {
	@Override
	public void setUp() {
		primitive = new Square(new Point(1, 1), new Point(2, 42));
	}

	/**
	 * Test method for {@link Square#isInsidePrimitive(Point)}.
	 */
	@Test
	public void testIsInsidePrimitive() {
		for (int x = 1; x <= 3; x++) {
			for (int y = 1; y <= 3; y++) {
				assertInside(x, y);
			}
		}
		
		assertOutside(0, 0);
		assertOutside(4, 0);
	}
	
	/**
	 * Test method for {@link Square#getBoundingBox()}.
	 */
	@Test
	public void testGetBoundingBox() {
		BoundingBox bbox = primitive.getBoundingBox();
		assertEquals(new Point(1, 1), bbox.getUpperLeftCorner());
		assertEquals(new Point(3, 3), bbox.getLowerRightCorner());
	}
}
