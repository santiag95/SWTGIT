package org.iMage.plugins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Knows all available plug-ins and is responsible for using the service loader
 * API to detect them.
 *
 */
public final class PluginManager {

	/**
	 * No constructor for utility class.
	 */
	private PluginManager() {
	}

	/**
	 * Return all available {@link JmjrstPlugin}s sorted accordingly to their
	 * priority.
	 * 
	 * @return all available plug-ins sorted according to their priority in
	 *         ascending order.
	 */
	public static List<JmjrstPlugin> getPlugins() {
		// TODO: implement me
		ServiceLoader<JmjrstPlugin> sL = ServiceLoader.load(JmjrstPlugin.class);
		
		List<JmjrstPlugin> plugs = new ArrayList<>();
		
		for (final JmjrstPlugin pl : sL) {
			plugs.add(pl);
		}
		
		Collections.sort(plugs);
		
		return plugs;
	}
}
