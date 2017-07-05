package org.iMage.geometrify.gui;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import org.iMage.plugins.JmjrstPlugin;
import org.iMage.plugins.PluginManager;

public class ChooseFilterList extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> box;
	private static Vector<String> filename;
	
	public ChooseFilterList() {
		super("Filter chooser");
		setLayout(new FlowLayout());
		
		filename = new Vector<>();
		Iterator<JmjrstPlugin> plugins = PluginManager.getPlugins().iterator();

		while (plugins.hasNext()) {
			final JmjrstPlugin plugin = plugins.next();
			
			// add menu item for plug-in
			filename.add(plugin.getName());

		}
		
		box = new JComboBox<>(filename);
		
		box.addItemListener(
					new ItemListener() {
						
						@Override
						public void itemStateChanged(ItemEvent e) {
							if (e.getStateChange() == ItemEvent.SELECTED) {
								
							}
							
						}
					}
				);
		
		add(box);
		
	}
	
}
