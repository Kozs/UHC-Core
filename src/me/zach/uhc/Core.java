package me.zach.uhc;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import me.zach.uhc.content.ContentManager;
import me.zach.uhc.content.WorldBorderHandler;
import me.zach.uhc.content.commands.ContentCommands;
import me.zach.uhc.features.FeatureManager;
import me.zach.uhc.player.UHCPlayerManager;

public class Core extends JavaPlugin {
	
	public static FileConfiguration serverConfig;
	public static CustomConfigManager configSettings = CustomConfigManager.getInstance();
	public static ConfigConstants con;
	public FeatureManager featureManager;
	public static Logger log;
	public static ConfigHandler configHandler;
	public static ContentCommands contentCmd;
	public static UHCPlayerManager uhcPM;
	public WorldBorderHandler wb;
	private ContentManager cM;
	private static Core instance;
	
	public void onEnable() {
		instance = this;
		
		saveDefaultConfig();
		serverConfig = getConfig();
		configSettings.setup(this);
		con = new ConfigConstants(this);
		log = getLogger();
		
		wb = new WorldBorderHandler(this);
		uhcPM = new UHCPlayerManager(this);
		contentCmd = new ContentCommands(this);
		configHandler = new ConfigHandler(this);
		featureManager = new FeatureManager(this);
		cM = new ContentManager(this);
	}
	
	public static Core getInstance() {
		return instance;
	}
	
	public WorldGuardPlugin getWorldGuard() {
	    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    if ((plugin == null) || (!(plugin instanceof WorldGuardPlugin))) {
	    return null;
	    }
	 
	    return (WorldGuardPlugin)plugin;
	}

	public ContentManager getcM() {
		return cM;
	}

	public void setcM(ContentManager cM) {
		this.cM = cM;
	}
	
}
