package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;

public class Rectangle implements IPrimitive {

	private BoundingBox boundingBox;
	private Color color;
	
	protected Point aDown;
	protected Point aUp;
	protected Point bDown;
	protected Point bUp;
	
	public Rectangle(Point a, Point b) {
		int xMIN = (int) Math.min(a.getX(), b.getX());
		int xMAX = (int) Math.max(a.getX(), b.getX());
		int yMIN = (int) Math.min(a.getY(), b.getY());
		int yMAX = (int) Math.max(a.getY(), b.getY());
		
		aDown = new Point(xMIN, yMIN);
		aUp = new Point(xMIN, yMAX);
		bDown = new Point(xMAX, yMIN);
		bUp = new Point(xMAX, yMAX);
		
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
