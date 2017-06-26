package org.iMage.plugins;

import java.util.List;
import java.util.Iterator;

public class IterablePlugins implements Iterable<JmjrstPlugin>{

	private JmjrstPlugin[] pluginList;
    private int currentSize;
    
	public IterablePlugins(List<JmjrstPlugin> p) {
		
		pluginList = new JmjrstPlugin[p.size()];
		pluginList = p.toArray(pluginList);
        this.currentSize = pluginList.length;
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
