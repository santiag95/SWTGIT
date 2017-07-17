package org.iMage.geometrify.parallel;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.iMage.geometrify.IPrimitive;
import org.iMage.geometrify.NonRandomPointGenerator;
import org.iMage.geometrify.Triangle;
import org.iMage.geometrify.TrianglePictureFilter;
import org.junit.Test;

public class ParallelTest {
	private static final int HEX_FF = 0xFF;
	private BufferedImage imSequence = null;
	private BufferedImage imParallel = null;
	
	/**
	 * Tests if the duration of the Parallel Implementation 
	 *   is lower than the Sequential
	 */
	@Test
	public void testTime() 
	{
		BufferedImage img = null;
		try 
		{
		    img = ImageIO.read(new File("/Users/santiagotafur/Desktop/dices_alpha.png")); 
		    
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		
		NonRandomPointGenerator nrpgPara = new NonRandomPointGenerator(img.getWidth(), img.getHeight());
	    NonRandomPointGenerator nrpgNormal = new NonRandomPointGenerator(img.getWidth(), img.getHeight());
	    ParallelTrianglePictureFilter pf = new ParallelTrianglePictureFilter(nrpgPara);
	    TrianglePictureFilter nf = new TrianglePictureFilter(nrpgNormal);
	    
	    
		
		long startTime2 = System.nanoTime();
		imSequence = nf.apply(img, 100, 50);
		long endTime2 = System.nanoTime();
		long duration2 = (endTime2 - startTime2);
	   
		long startTime1 = System.nanoTime();
		imParallel = pf.apply(img, 100, 50);
		long endTime1 = System.nanoTime();
		long duration1 = (endTime1 - startTime1);
		
		File outputfile = new File("/Users/santiagotafur/Desktop/parallelDices.png");
	    File outputfile2 = new File("/Users/santiagotafur/Desktop/normalDices.png");
	    
	    try {
			ImageIO.write(imParallel, "png", outputfile);
			ImageIO.write(imSequence, "png", outputfile2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		assertTrue(duration1<duration2);
		
	}
	
	/**
	 * Test through a Simulation of both implementations if 
	 *   every Primitive they generate are the same.
	 */
	@Test
	public void testEquality() {
		BufferedImage image = null;
		try 
		{
		    image = ImageIO.read(new File("/Users/santiagotafur/Desktop/dices_alpha.png")); 
		    
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		
		
		boolean equal = true;
		List<IPrimitive> bestParallel = simulationParallelTriangle(image, 20, 4);
		List<IPrimitive> bestSequential = simulationSequentialTriangle(image, 20, 4);
		
		if (bestParallel.size() == bestSequential.size()) {
			for (int i = 0; i < bestSequential.size(); i++) {
				IPrimitive parallelPrimitive = bestParallel.get(i);
				IPrimitive sequentialPrimitive = bestSequential.get(i);
				
				Point aLRPara = parallelPrimitive.getBoundingBox().getLowerRightCorner();
				Point aULPara = parallelPrimitive.getBoundingBox().getLowerRightCorner();
				
				Point aLRSeq = sequentialPrimitive.getBoundingBox().getLowerRightCorner();
				Point aULSeq = sequentialPrimitive.getBoundingBox().getLowerRightCorner();
				
				
				boolean lowerRightPoint = aLRPara.equals(aLRSeq);
				boolean upperLeftBound = aULPara.equals(aULSeq);
				boolean color = parallelPrimitive.getColor().equals(sequentialPrimitive.getColor());
				
				if(!(lowerRightPoint && upperLeftBound && color)) {
					equal = false;
					break;
				}
			}
		}
		else {
			equal = false;
		}
		
		assertTrue(equal);
	}
	
	/**
	 * Simulates the implementation of the ParallelFilter
	 * 
	 * @param image The image for the simulation.
	 * @param numberOfIterations number of iterations for the simulation.
	 * @param numberOfSamples number of samples for the iteration.
	 * @return List with the primitives generated.
	 */
	private static List<IPrimitive> simulationParallelTriangle(BufferedImage image, int numberOfIterations, int numberOfSamples) {
		NonRandomPointGenerator nrpgPara = new NonRandomPointGenerator(image.getWidth(), image.getHeight());
	    ParallelTrianglePictureFilter pf = new ParallelTrianglePictureFilter(nrpgPara);
		List<IPrimitive> bestFigures = new ArrayList<IPrimitive>();
	    
		int width = image.getWidth();
		int height = image.getHeight();

		// construct "empty" image
		BufferedImage result = new BufferedImage(width, height, image.getType());

		for (int i = 0; i < numberOfIterations; i++) {
			IPrimitive bestPrimitive = null;
			bestPrimitive = pf.beginThreads(numberOfSamples, image, result);
			
			bestFigures.add(bestPrimitive);
		}
		return bestFigures;
		
	}
	
	/**
	 * Simulates the implementation of the PictureFilter
	 * 
	 * @param image The image for the simulation.
	 * @param numberOfIterations number of iterations for the simulation.
	 * @param numberOfSamples number of samples for the iteration.
	 * @return List with the primitives generated.
	 */
	private static List<IPrimitive> simulationSequentialTriangle(BufferedImage image, int numberOfIterations, int numberOfSamples) {
		NonRandomPointGenerator nrpgSeq = new NonRandomPointGenerator(image.getWidth(), image.getHeight());
		List<IPrimitive> bestFigures = new ArrayList<IPrimitive>();
		
		
		int width = image.getWidth();
		int height = image.getHeight();

		// construct "empty" image
		BufferedImage result = new BufferedImage(width, height, image.getType());

		for (int i = 0; i < numberOfIterations; i++) {
			int bestDistance = Integer.MAX_VALUE;
			IPrimitive bestPrimitive = null;

			for (int s = 0; s < numberOfSamples; s++) {
				IPrimitive sample = new Triangle(nrpgSeq.nextPoint(), nrpgSeq.nextPoint(), nrpgSeq.nextPoint());
				sample.setColor(calculateColor(image, sample));
				int distance = calculateDifference(image, result, sample);

				if (distance <= bestDistance) {
					bestDistance = distance;
					bestPrimitive = sample;
				}
			}
			bestFigures.add(bestPrimitive);
		}
		return bestFigures;

		
	}
	
	private static Color calculateColor(BufferedImage original, IPrimitive primitive) {
		final int bandwidth = (original.getColorModel().hasAlpha()) ? 4 : 3;

		final int width = original.getWidth();
		final int height = original.getHeight();

		final Point start = primitive.getBoundingBox().getUpperLeftCorner();
		final Point end = primitive.getBoundingBox().getLowerRightCorner();

		int numPixels = 0;
		int red = 0;
		int green = 0;
		int blue = 0;
		int alpha = 0;

		final int wStart = Math.max(0, start.x);
		final int wEnd = Math.min(width - 1, end.x);
		final int hStart = Math.max(0, start.y);
		final int hEnd = Math.min(height - 1, end.y);

		final int rectangleWidth = wEnd - wStart + 1;
		final int rectangleHeight = hEnd - hStart + 1;

		int[] imgPixels = new int[rectangleWidth * rectangleHeight * bandwidth];
		original.getRaster().getPixels(wStart, hStart, rectangleWidth, rectangleHeight, imgPixels);

		Point currPixel = new Point();

		for (int y = 0; y < rectangleHeight; y++) {
			for (int x = 0; x < rectangleWidth; x++) {
				currPixel.setLocation(x + wStart, y + hStart);
				if (primitive.isInsidePrimitive(currPixel)) {
					int pixelIndex = (y * rectangleWidth + x) * bandwidth;
					numPixels++;

					if (bandwidth == 4) {
						alpha += imgPixels[pixelIndex + 3];
					}
					red += imgPixels[pixelIndex + 2];
					green += imgPixels[pixelIndex + 1];
					blue += imgPixels[pixelIndex];
				}
			}
		}
		// normalize color values
		if (numPixels > 0) {
			alpha = alpha / numPixels;
			red = red / numPixels;
			green = green / numPixels;
			blue = blue / numPixels;
		}
		if (original.getColorModel().hasAlpha()) {
			return new Color(red, green, blue, alpha);
		} else {
			return new Color(red, green, blue);
		}
	}
	
	private static int calculateDifference(BufferedImage original, BufferedImage current, IPrimitive primitive) {
		assert (primitive.getColor() != null) : "Average color of primitive has not been calculated, yet";

		final int bandwidth = (original.getColorModel().hasAlpha()) ? 4 : 3;

		final int width = original.getWidth();
		final int height = original.getHeight();

		int[] orgPixels = new int[width * height * bandwidth];
		int[] currPixels = new int[width * height * bandwidth];

		current.getRaster().getPixels(0, 0, width, height, currPixels);
		original.getRaster().getPixels(0, 0, width, height, orgPixels);

		Point currPixel = new Point();

		int difference = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixelIndex = (y * width + x) * bandwidth;
				currPixel.setLocation(x, y);

				// inside Primitive use calculated avg between current and
				// primitive color as value to calculate difference with
				if (primitive.isInsidePrimitive(currPixel)) {
					for (int n = 0; n < bandwidth; n++) {
						difference += Math.abs(orgPixels[pixelIndex + n]
								- ((currPixels[pixelIndex + n] + ((primitive.getColor().getRGB() >> n * 8) & HEX_FF)) / 2));
					}
				} else {
					for (int n = 0; n < bandwidth; n++) {
						difference += Math.abs(orgPixels[pixelIndex + n] - currPixels[pixelIndex + n]);
					}
				}
			}
		}
		return difference;
	}
	
}
