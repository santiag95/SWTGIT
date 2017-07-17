package org.iMage.geometrify.parallel;

import java.awt.Point;

import org.iMage.geometrify.NonRandomPointGenerator;

public class Practice {

	
	
	public static void main (String[] args) {
		NonRandomPointGenerator nrpg = new NonRandomPointGenerator(800, 600);
		for(int i = 0; i < 21; i++) {
			Point a = nrpg.nextPoint();
			boolean tri = false;
			if ((i+1) % 3 == 1) {
				tri = true;
			}
			
			if(tri) {
				System.out.println("-Triangulo- "+ i+1 +".Punto : "+a.getX()+" | "+a.getY());
			}
			else {
				System.out.println(i+1 +".Punto : "+a.getX()+" | "+a.getY());
			}
		}
	}
}
