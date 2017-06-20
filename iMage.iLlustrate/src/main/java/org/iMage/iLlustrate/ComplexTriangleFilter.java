package org.iMage.iLlustrate;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitive;
import org.iMage.geometrify.TrianglePictureFilter;

public class ComplexTriangleFilter extends TrianglePictureFilter {

	private RunFrame ref;
	
	public ComplexTriangleFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	
	
	public BufferedImage apply1(BufferedImage image, int numberOfIterations, int numberOfSamples, File file) {
		int width = image.getWidth();
		int height = image.getHeight();

		ref = new RunFrame(file, image, numberOfIterations, numberOfSamples);
		
		BufferedImage result = new BufferedImage(width, height, image.getType());

		for (int i = 0; i < numberOfIterations; i++) {
			int bestDifference = Integer.MAX_VALUE;
			IPrimitive bestPrimitive = null;

			for (int s = 0; s < numberOfSamples; s++) {
				IPrimitive sample = generatePrimitive();
				sample.setColor(calculateColor(image, sample));
				int difference = calculateDifference(image, result, sample);

				if (difference <= bestDifference) {
					bestDifference = difference;
					bestPrimitive = sample;
				}
			}

			addToImage(result, bestPrimitive);
			ref.actualizeIcon(result, i);
			
		
		}
		ref.actualizeIcon(result, numberOfIterations);
		ref.setEndResult(result);
		return result;
	}
	
	

}
