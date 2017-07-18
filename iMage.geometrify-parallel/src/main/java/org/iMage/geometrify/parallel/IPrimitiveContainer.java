package org.iMage.geometrify.parallel;

import org.iMage.geometrify.IPrimitive;

/**
 * Encapsulates a IPrimitive with its specific distance .
 * @author santiagotafur
 *
 */
public class IPrimitiveContainer {


	private IPrimitive value;
	private int distance;

    /**
     * Initializes a container with a determined Primitive.
     * @param initialValue the Primitive to be contained.
     */
    public IPrimitiveContainer(IPrimitive initialValue) {
        value = initialValue;
        distance = Integer.MAX_VALUE;
    }
    /**
     * @return the primitive.
     */
    public IPrimitive getPrimitive() {
    	return value;
    }
    
    /**
     * @param a sets the primitive.
     */
    public void setValue(IPrimitive a) {
    	value = a;
    }
    
	/**
	 * @return the distance.
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * @param distance sets the distance.
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}
    
    
}
