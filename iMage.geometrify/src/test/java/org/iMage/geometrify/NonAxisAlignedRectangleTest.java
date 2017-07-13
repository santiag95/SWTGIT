package org.iMage.geometrify;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

/**
 * Test class for {@link NonAxisAlignedRectangle}.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public class NonAxisAlignedRectangleTest extends AbstractPrimitiveTest {
	@Override
	public void setUp() {
		Point a = new Point(3, 0);
		Point b = new Point(5, 2);
		Point p = new Point(2, 3);
		primitive = new NonAxisAlignedRectangle(a, b, p);
	}

	/**
	 * Test method for {@link NonAxisAlignedRectangle#isInsidePrimitive(Point)}.
	 */
	@Test
	public void testIsInsidePrimitive() {
		boolean[][] pixel = new boolean[][]{
				{ false, false, false, true, false, false },
				{ false, false,  true, true,  true, false },
				{ false,  true,  true, true,  true,  true, false },
				{ false, false,  true, true,  true, false },
				{ false, false, false, true, false, false }
		};
		for (int y = 0; y < pixel.length; y++) {
			for (int x = 0; x < pixel[y].length; x++) {
				if (pixel[y][x]) {
					assertInside(x, y); 
				} else {
					assertOutside(x, y);
				}
			}
		}
		assertOutside(3, 5);
	}

	/**
	 * Test method for {@link NonAxisAlignedRectangle#getBoundingBox()}.
	 */
	@Test
	public void testGetBoundingBox() {
		BoundingBox bbox = primitive.getBoundingBox();
		assertEquals(new Point(1, 0), bbox.getUpperLeftCorner());
		assertEquals(new Point(5, 4), bbox.getLowerRightCorner());
	}
}
