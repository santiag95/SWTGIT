package org.iMage.geometrify.parallel;

public class IntContainer {

	private int value;

    public IntContainer(int initialValue) {
        value = initialValue;
    }
    public int getValue(){
    	return value;
    }
    public void setValue(int a) {
    	value = a;
    }
    public void add(int a) {
    	value = value + a;
    }
}
