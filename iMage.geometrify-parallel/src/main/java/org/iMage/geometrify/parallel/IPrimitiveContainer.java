package org.iMage.geometrify.parallel;

import org.iMage.geometrify.IPrimitive;

public class IPrimitiveContainer {


	private IPrimitive value;
	private int distance;

    public IPrimitiveContainer(IPrimitive initialValue) {
        value = initialValue;
        distance = Integer.MAX_VALUE;
    }
    public IPrimitive getPrimitive(){
    	return value;
    }
    public void setValue(IPrimitive a) {
    	value = a;
    }
    
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
    
    
}
