package me.zach.uhc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {

	private Core c;

	private FileConfiguration config;

	public ConfigHandler(Core c) {
		this.c = c;
		this.config = c.getConfig();
		buildDefaultConfig();
	}
	
	public List<String> goldenHeadsLore() {
		List<String> newList = new ArrayList<String>();
		newList.add("&4Provides 4 extra hearts!");
		newList.add("&bProvides Absorption");
		return newList;
	}
	
	public void updateConfigValue(String path, Object value) {
		config.set(path, value);
		save();
		Core.con.loadConfigurationVars();
	}
	
	@SuppressWarnings("static-access")
	public void buildDefaultConfig() { //builds the config for the user
		ConfigConstants cc = ConfigConstants.getConstants;
		config.addDefault(cc.CONFIG_NOPERM_ERR_MSG, "&cYou do not have the proper permissions for this command!");
		config.addDefault(cc.CONFIG_UHC_MSG_PREFIX, "&8[&b&lSky&4&lPvP&8] &7");
		//features
		config.addDefault(cc.CONFIG_GHAST_DROP_AMT, 10);
		config.addDefault(cc.CONFIG_GOLDENHEAD_LORE, goldenHeadsLore());
		config.addDefault(cc.CONFIG_GOLDENHEAD_ABSORBTION, 25);
		config.addDefault(cc.CONFIG_GOLDENHEAD_ABSORBTION_HEARTS, 8);
		config.addDefault(cc.CONFIG_GOLDENHEAD_ABSORBTION_LEVEL, 0);
		
		//end features
		config.addDefault(cc.CONFIG_COMBATLOGGER_TAGTIME, 10);
		
		//content config
		config.addDefault(cc.CONFIG_CONTENT_FEEDALL, "&8Your urge to eat fades...");
		config.addDefault(cc.CONFIG_CONTENT_FEEDPLAYER, "&8Your urge to eat fades....");
		config.addDefault(cc.CONFIG_CONTENT_FEEDINVALID, "&cInvalid Target! Try the name again!");
		config.addDefault(cc.CONFIG_CONTENT_HEALALL, "&4You have been completely healed!");
		config.addDefault(cc.CONFIG_CONTENT_HEALPLAYER, "&4You have been personally healed!");
		config.addDefault(cc.CONFIG_CONTNET_HEALINVALID, "&cInvalid Target! Cannot heal Invalid Target!");
		config.addDefault(cc.CONFIG_CONTENT_EXP_CLEARALL, "&cYour EXP has been removed!");
		config.addDefault(cc.CONFIG_CONTENT_EXP_CLEARPLAYER, "&cSomeone has removed your Experience!");
		config.addDefault(cc.CONFIG_CONTENT_EXP_CLEARINVALID, "&cTarget invalid! Cannot clear users Experience!");
		config.addDefault(cc.CONFIG_CONTENT_APPLE_RATE, 20);
		config.options().copyDefaults(true);
		save();
		
	}
	
	public void save() { // here to save some verbose java
		c.saveConfig();
	}

}
