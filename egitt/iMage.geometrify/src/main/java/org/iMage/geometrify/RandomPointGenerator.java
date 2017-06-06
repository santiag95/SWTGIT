package org.iMage.geometrify;

import java.awt.Point;
import java.util.Random;
/**
 * Provides an infinite source of points at random coordinates within a given
 * range.
 *
 * @author Dominic Ziegler
 * @version 1.0
 */
public class RandomPointGenerator implements IPointGenerator {

	/**
	 * The maximum X coordinate for the point thats going to be created.
	 */
	private int maxX;
	/**
	 * The maximum Y coordinate for the point thats going to be created.
	 */
	private int maxY;
	
	/**
	 * Constructs the generator for points within the specified coordinate
	 * space.
	 *
	 * @param width
	 *            the maximum x coordinate
	 * @param height
	 *            the maximum y coordinate
	 */
	public RandomPointGenerator(int width, int height) {
		maxX = width;
		maxY = height;
	}
	
	
	/**
	 * Generates a new random point given the coordinates in the constructor.
	 * @return Point the Point generated within the random coordinates.
	 */
	public Point nextPoint() {
		Random rand = new Random();
		int randomX = rand.nextInt(maxX);
		int randomY = rand.nextInt(maxY); 
		
		return new Point(randomX, randomY);
	}
}
