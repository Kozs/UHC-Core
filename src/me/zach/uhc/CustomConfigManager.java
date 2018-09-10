package me.zach.uhc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.zach.uhc.features.Features;

public class CustomConfigManager {

	static CustomConfigManager instance = new CustomConfigManager();

	public static CustomConfigManager getInstance() {
		return instance;
	}

	/**
	 * Custom Config Files
	 */

	private FileConfiguration features;
	private File ffile;

	public void setup(JavaPlugin p) {
		ffile = new File(p.getDataFolder(), "/features.yml");
		if (!ffile.exists()) {
			try {
				ffile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		setFeatures(YamlConfiguration.loadConfiguration(ffile));
	}
	
	public void save() {
		try {
			features.save(ffile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FileConfiguration getFeatures() {
		return features;
	}

	public void setFeatures(FileConfiguration features) {
		this.features = features;
	}

}
