package org.iMage.prizm.plugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.iMage.plugins.JmjrstPlugin;
import org.iMage.plugins.PluginPriority;
import org.jis.Main;
import org.kohsuke.MetaInfServices;

/**
 * Hello world!
 *
 */
@MetaInfServices(JmjrstPlugin.class)
public class Prizm extends JmjrstPlugin {
	private Main jmjrst;

	@Override
	public PluginPriority getPriority() {
		return priority;
	}

	@Override
	public void setPriority(PluginPriority priority) {
		this.priority = priority;
	}

	@Override
	public String getMenuText() {
		return "Prizm plug-in";
	}

	@Override
	public String getName() {
		return "Prizm";
	}

	@Override
	public void init(Main main) {
		this.jmjrst = main;
		System.out.println("iMage: Ihr freundlicher Nutzerdatensammler seit 2016!");
	}

	@Override
	public void run() {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		Date date = new Date();
		System.err.println("Ueberwachung laeuft seit " + dateFormat.format(date));
	}

	@Override
	public boolean isConfigurable() {
		return true;
	}

	@Override
	public void configure() {
		PluginPriority priority = getPriority();
		String title = getName() + " (" + (priority != null ? priority : "no") + " priority)";
		JOptionPane.showMessageDialog(jmjrst, "Ueberwache Nutzer " + System.getProperty("user.name"), title,
				JOptionPane.INFORMATION_MESSAGE);
	}
}
