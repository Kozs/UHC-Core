package me.zach.uhc.features;

import java.util.ArrayList;

import me.zach.uhc.Core;
import me.zach.uhc.features.events.listener.ActiveFeatureListener;
import me.zach.uhc.features.events.listener.DeactiveFeatureListener;
import me.zach.uhc.features.impl.CaveSpiderPoision;
import me.zach.uhc.features.impl.DeathLightning;
import me.zach.uhc.features.impl.DisableNatRegen;
import me.zach.uhc.features.impl.EpearlDamage;
import me.zach.uhc.features.impl.GhastDrops;
import me.zach.uhc.features.impl.GoldenHeads;
import me.zach.uhc.features.impl.NetherPortals;
import me.zach.uhc.features.impl.PlayerHeads;
import me.zach.uhc.features.impl.PotionNerf;
import me.zach.uhc.features.impl.WitchSpawns;

public class FeatureManager {

	public ArrayList<Feature> features = new ArrayList<Feature>();
	
	private FeatureCommands featureCmds; //Created this incase we need to access this later, wouldn't know why but yolo
	private Core c;
	
	public FeatureManager(Core c) {
		this.c = c;
		setupFeatures();
		
		//init command class
		featureCmds = new FeatureCommands(c);
		c.getCommand("feature").setExecutor(featureCmds);
		
		//init event listeners for features
		c.getServer().getPluginManager().registerEvents(new ActiveFeatureListener(), c);
		c.getServer().getPluginManager().registerEvents(new DeactiveFeatureListener(), c);
		
		//Loads all of the active Features onto server
		loadFeatureListeners();
	}
	
	public void setupFeatures() {
		features.add(new CaveSpiderPoision(c));
		features.add(new DeathLightning(c));
		features.add(new DisableNatRegen(c));
		features.add(new EpearlDamage(c));
		features.add(new GhastDrops(c));
		features.add(new GoldenHeads(c));
		features.add(new NetherPortals(c));
		features.add(new PlayerHeads(c));
		features.add(new PotionNerf(c));
		features.add(new WitchSpawns(c));
	}

	private void loadFeatureListeners() {
		for (Feature f: features) {
			if (f.isActive()) {
				Core.log.info("Reactivated Feature: " + f.getFeature().getName());
				f.activateListeners();
			}
		}
		
	}
	
	public Feature getFeature(Features featureType) {
		Feature goal = null;
		for (Feature f: features) {
			if (f.getFeature().equals(featureType)) {
				goal = f;
			}
		}
		return goal;
	}
	
	public void addFeature(Feature feature) {
		features.add(feature);
	}
	
	

}
