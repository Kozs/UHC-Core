package me.zach.uhc.features;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import me.zach.uhc.ConfigConstants;
import me.zach.uhc.Core;

public abstract class Feature implements Listener {
	
	Core c;
	
	public static ConfigConstants con; //verbose reasons
	
	private PluginManager pluginManager;
	
	public Feature(Core c) {
		this.c = c;
		setPluginManager(c.getServer().getPluginManager());
	}
	
	public abstract void run();
	public abstract void end();
	public abstract void activateListeners();
	public abstract void deactiveListeners();
	
	public abstract void setActive();
	public abstract boolean isActive();
	public abstract Features getFeature();
	
	public String getRoot() {
		return "features.";
	}
	
	public FileConfiguration getFile() {
		return Core.configSettings.getFeatures();
	}
	
	public Core getCore() {
		return c;
	}

	public PluginManager getPluginManager() {
		return pluginManager;
	}

	public void setPluginManager(PluginManager pluginManager) {
		this.pluginManager = pluginManager;
	}
}
