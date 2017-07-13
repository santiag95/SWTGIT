package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Abstract test class for primitives.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public abstract class AbstractPrimitiveTest {
	protected IPrimitive primitive;
	
	/**
	 * Assert that the given point is inside the primitive under test.
	 * 
	 * @param x the x-coordinate of the point
	 * @param y the y-coordinate of the point
	 */
	protected void assertInside(int x, int y) {
		assertTrue("(" + x + "|" + y + ") must be inside", primitive.isInsidePrimitive(new Point(x, y)));
	}
	
	/**
	 * Assert that the given point is outside the primitive under test.
	 * 
	 * @param x the x-coordinate of the point
	 * @param y the y-coordinate of the point
	 */
	protected void assertOutside(int x, int y) {
		assertFalse("(" + x + "|" + y + ") must be outside", primitive.isInsidePrimitive(new Point(x, y)));
	}	
	
	/**
	 * Set up an instance of the primitive under test.
	 */
	@Before
	public abstract void setUp();
	
	/**
	 * Test case for {@link IPrimitive#setColor()} and {@link IPrimitive#getColor()}.
	 */
	@Test
	public void testColor() {
		primitive.setColor(Color.PINK);
		assertEquals(Color.PINK, primitive.getColor());
	}
}
