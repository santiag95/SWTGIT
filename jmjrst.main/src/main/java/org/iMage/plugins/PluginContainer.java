package org.iMage.plugins;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A container for the iterable data structure
 * 
 * @author Tobias Hey
 *
 */
public class PluginContainer implements Iterable<JmjrstPlugin> {

	private List<JmjrstPlugin> list;

	/**
	 * Construct a new {@link PluginContainer} with a given {@link List}
	 * 
	 * @param list
	 *            the {@link List} to iterate over
	 */
	public PluginContainer(List<JmjrstPlugin> list) {
		Collections.sort(list);
		Collections.reverse(list);

		this.list = list;
	}

	@Override
	public Iterator<JmjrstPlugin> iterator() {

		return new PluginIterator();
	}

	private class PluginIterator implements Iterator<JmjrstPlugin> {

		private int position = 0;

		@Override
		public boolean hasNext() {
			return list.size() > position;
		}

		@Override
		public JmjrstPlugin next() {
			if (hasNext()) {
				return list.get(position++);
			} else {
				throw new NoSuchElementException("Ende");
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Nö");
		}
	}
}
