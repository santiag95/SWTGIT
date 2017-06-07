package org.iMage.prizm.plugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;


public class HalloWeltPlugin extends JmjrstPlugin {
	private Main jmjrstSWT;
	
	public String getMenuText() {
		return "HalloWeltPlugin";
	}
	
	public String getName() {
		return "Hallo-Welt-Plugin";
	}
	
	public void run() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.err.println("Ueberwachung laeuft seit " + dateFormat.format(date));
	}
	
	public void init(Main main) {
		jmjrstSWT = main;
		System.out.println("„iMage: Ihr freundlicher Nutzerdatensammler seit 2016!");
	}
	
	public void setPriority(PluginPriority priority) {
		this.priority = priority;
	}
	
	public PluginPriority getPriority() {
		return this.priority;
	}
	
	public boolean isConfigurable() {
		return true;
	}
	
	public void configure() {
		JOptionPane.showMessageDialog(jmjrstSWT, getName(), this.priority.name(),
				"Ueberwache Nutzer " + System.getProperty("user.name"), JOptionPane.INFORMATION_MESSAGE);
	}
}
