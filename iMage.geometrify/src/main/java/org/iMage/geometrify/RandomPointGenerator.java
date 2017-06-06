package org.iMage.geometrify;

import java.awt.Point;
import java.util.Random;
<<<<<<< HEAD

import org.iMage.geometrify.IPointGenerator;

/**
 * Provides an infinite source of points at random coordinates within a given range.
 * 
=======
/**
 * Provides an infinite source of points at random coordinates within a given
 * range.
 *
>>>>>>> d3e926f0ce8bc5aa499085a74d34ed4e5a7cdaa7
 * @author Dominic Ziegler
 * @version 1.0
 */
public class RandomPointGenerator implements IPointGenerator {
<<<<<<< HEAD
	private int width;
	private int height;
	
	private Random rand = new Random();
	
	/**
	 * Constructs the generator for points within the specified coordinate space.
	 * 
	 * @param width  the maximum x coordinate
	 * @param height the maximum y coordinate
	 */
	public RandomPointGenerator(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public Point nextPoint() {
		return new Point(rand.nextInt(width), rand.nextInt(height));
=======

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
>>>>>>> d3e926f0ce8bc5aa499085a74d34ed4e5a7cdaa7
	}
}
