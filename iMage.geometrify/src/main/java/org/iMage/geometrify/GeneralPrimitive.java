package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;

/**
 * A IPrimitive shape to model the rest.
 *
 * @author Santiago Tafur
 * @version 1.0
 */
public abstract class GeneralPrimitive implements IPrimitive {

	
	/**
	 * color of the primitive.
	 */
	private Color color;
	/**
	 * boundingBox that defines the primitive.
	 */
	private BoundingBox boundingBox;
	
	/* (non-Javadoc)
	 * @see org.iMage.geometrify.IPrimitive#getBoundingBox()
	 */
	@Override
	public BoundingBox getBoundingBox() {
		
		return boundingBox;
	}

	/* (non-Javadoc)
	 * @see org.iMage.geometrify.IPrimitive#getColor()
	 */
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	

	/* (non-Javadoc)
	 * @see org.iMage.geometrify.IPrimitive#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		this.color = c;
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.iMage.geometrify.IPrimitive#isInsidePrimitive(java.awt.Point)
	 */
	public abstract boolean isInsidePrimitive(Point a);

}
