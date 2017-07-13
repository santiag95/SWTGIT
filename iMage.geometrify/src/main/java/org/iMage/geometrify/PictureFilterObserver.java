package org.iMage.geometrify;

import java.awt.image.BufferedImage;

/**
 * Interface for classes that want to be informed about the progress of the 
 * application of a {@link PictureFilter}.
 * 
 * @author Dominic Ziegler
 * @version 1.0
 */
public interface PictureFilterObserver {
	/**
	 * This method is called by an observed {@link PictureFilter} after each 
	 * iteration. 
	 * 
	 * @param image
	 *            the current image
	 */
	void update(BufferedImage image);
}
