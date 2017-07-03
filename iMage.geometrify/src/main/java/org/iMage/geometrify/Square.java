package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;

public class Square implements IPrimitive {

	private BoundingBox boundingBox;
	private Color color;
	
	protected Point aDown;
	protected Point aUp;
	protected Point bDown;
	protected Point bUp;
	
	public Square(Point p, int length) {
		aDown = p;
		aUp = new Point(p.x, p.y + length);
		bDown = new Point(p.x + length, p.y);
		bUp = new Point(p.x + length, p.y + length);
		
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
	public boolean isInsidePrimitive(Point arg) {
		// TODO Auto-generated method stub
		boolean betweenX = false;
		boolean betweenY = false;
		
		if(arg.getX() > aDown.getX() && arg.getX() < bDown.getX()) {
			betweenX = true;
		}
		
		if(arg.getY() > aDown.getY() && arg.getY() < bUp.getY()) {
			betweenY = true;
		}
		
		
		
		return (betweenX && betweenY);
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		this.color = c;
		
	}

}
