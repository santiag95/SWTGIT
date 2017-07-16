package org.iMage.geometrify.parallel;

import java.awt.Point;

import org.iMage.geometrify.NonRandomPointGenerator;

public class Practice {

	NonRandomPointGenerator nrp = new NonRandomPointGenerator(100, 100);
	
	
	
	public Practice() {
		for (int i = 0; i<30+1; i++) {
			Point a = nrp.nextPoint();
			System.out.println(i+1 + "Punto : " + a.getX() + "|" + a.getY());
		}
	}
	
	public static void main(String[] args) {
		Practice n = new Practice();
	}
	
}
