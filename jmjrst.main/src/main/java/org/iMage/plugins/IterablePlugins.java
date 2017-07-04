package org.iMage.plugins;

import java.util.List;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * @author santiagotafur
 * @version 1.0
 *
 */
public class IterablePlugins implements Iterable<JmjrstPlugin> {

	/**
	 * The list of plugins modeled as an Array.
	 */
	private JmjrstPlugin[] pluginList;
    /**
     * The current size of the list.
     */
    private int currentSize;
    
	/**
	 * Creates a new Iterable object based on a list.
	 * @param p the list of Plugins
	 */
	public IterablePlugins(List<JmjrstPlugin> p) {
		
		pluginList = new JmjrstPlugin[p.size()];
		pluginList = p.toArray(pluginList);
        this.currentSize = pluginList.length;
        sort();
	}

	/**
	 * Sorts the array of plugins.
	 */
	public void sort() {
		
		Arrays.sort(pluginList, new Comparator<JmjrstPlugin>() {
			@Override
			public int compare(JmjrstPlugin o1, JmjrstPlugin o2) {
				if (o1.getPriority() == null) {
					if (o2.getPriority() == null) {
						return 0;
					} else {
						return -1;
					}

				} else {
					if (o2.getPriority() == null) {
						return 1;
					} else {
						return o1.getPriority().compareTo(o2.getPriority());
					}
				}
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	
	@Override
	public Iterator<JmjrstPlugin> iterator() {
		Iterator<JmjrstPlugin> it = 
				
		/**
		 * @author santiagotafur
		 * @version 1.0
		 *
		 */
		new Iterator<JmjrstPlugin>() {

            /**
             * current index in the array.
             */
            private int currentIndex = 0;

            /* (non-Javadoc)
             * @see java.util.Iterator#hasNext()
             */
            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && pluginList[currentIndex] != null;
            }

            /* (non-Javadoc)
             * @see java.util.Iterator#next()
             */
            @Override
            public JmjrstPlugin next() {
                return pluginList[currentIndex++];
            }

            /* (non-Javadoc)
             * @see java.util.Iterator#remove()
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
	}
	
}
