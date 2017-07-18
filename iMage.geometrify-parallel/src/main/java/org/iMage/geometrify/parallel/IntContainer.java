package org.iMage.geometrify.parallel;

/**
 * Contains an integer.
 * @author santiagotafur
 *
 */
public class IntContainer {

	private int value;

    /**
     * Creates a container of an integer.
     * @param initialValue the initial value of the container.
     */
    public IntContainer(int initialValue) {
        value = initialValue;
    }
    /**
     * @return the value
     */
    public int getValue(){
    	return value;
    }
    /**
     * @param a sets the value
     */
    public void setValue(int a) {
    	value = a;
    }
    /**
     * Adds a into the value.
     * @param a to be added.
     */
    public void add(int a) {
    	value = value + a;
    }
}
