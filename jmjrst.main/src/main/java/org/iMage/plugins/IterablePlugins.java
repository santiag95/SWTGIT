package org.iMage.plugins;

import java.util.List;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class IterablePlugins implements Iterable<JmjrstPlugin>{

	private JmjrstPlugin[] pluginList;
    private int currentSize;
    
	public IterablePlugins(List<JmjrstPlugin> p) {
		
		pluginList = new JmjrstPlugin[p.size()];
		pluginList = p.toArray(pluginList);
        this.currentSize = pluginList.length;
        sort();
	}

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
	@Override
	public Iterator<JmjrstPlugin> iterator() {
		Iterator<JmjrstPlugin> it = new Iterator<JmjrstPlugin>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && pluginList[currentIndex] != null;
            }

            @Override
            public JmjrstPlugin next() {
                return pluginList[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
	}
	
}
