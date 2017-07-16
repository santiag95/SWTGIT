package org.iMage.geometrify.parallel;

import org.iMage.geometrify.IPrimitive;

public class SamplePackage {

	IPrimitive sample = null;
	int distance = Integer.MAX_VALUE;
	
	public SamplePackage() {
		
	}

	public IPrimitive getSample() {
		return sample;
	}

	public void setSample(IPrimitive sample) {
		this.sample = sample;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
}
