package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;

public class Ellipse implements IPrimitive {

	protected Point aDown;
	protected Point aUp;
	protected Point bDown;
	protected Point bUp;
	protected Point center;
	
	private BoundingBox boundingBox;
	private Color color;
	private int xRadius;
	private int yRadius;
	
	
	public Ellipse(Point p, int width, int height) {
		this.center = p;
		this.xRadius = width / 2;
		this.yRadius = height / 2;
		aDown = new Point(p.x - xRadius, p.y - yRadius);
		aUp = new Point(p.x - xRadius, p.y + yRadius);
		bDown = new Point(p.x + xRadius, p.y - yRadius);
		bUp = new Point(p.x + xRadius, p.y + yRadius);
		
		this.boundingBox = new BoundingBox(aUp, bDown);
	}
	
	@Override
	public BoundingBox getBoundingBox() {
		// TODO Auto-generated method stub
		return boundingBox;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public boolean isInsidePrimitive(Point arg0) {
		 if (xRadius <= 0 || yRadius <= 0)
	            return false;
	        
	        Point normalized = new Point(arg0.x - center.x, arg0.y - center.y);

	        boolean total =  ((double)(normalized.x * normalized.y) / (xRadius * xRadius)) + 
	        		((double)(normalized.y * normalized.y) / (yRadius * yRadius)) <= 1.0;
	        
	        return total;
	}

	@Override
	public void setColor(Color arg0) {
		// TODO Auto-generated method stub
		this.color = arg0;
	}

}
