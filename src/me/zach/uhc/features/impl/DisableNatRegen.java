package me.zach.uhc.features.impl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import me.zach.uhc.Core;
import me.zach.uhc.features.Feature;
import me.zach.uhc.features.Features;

public class DisableNatRegen extends Feature {
	
	boolean active = Core.configSettings.getFeatures().getBoolean("features." + this.getClass().getSimpleName());
	
	public DisableNatRegen(Core c) {
		super(c);
	}

	@Override
	public void run() {
		activateListeners();
		active = true;
	}

	@Override
	public void end() {
		deactiveListeners();
		active = false;
	}

	@Override
	public void activateListeners() {
		getCore().getServer().getPluginManager().registerEvents(this, getCore());	
	}
	
	@EventHandler
	public void stopNaturalRegen(EntityRegainHealthEvent event) {
		event.setCancelled(true);
	}

	@Override
	public void deactiveListeners() {
		EntityRegainHealthEvent.getHandlerList().unregister(this);
		
	}

	@Override
	public Features getFeature() {
		// TODO Auto-generated method stub
		return Features.DisableNatRegen;
	}

	@Override
	public void setActive() {
		boolean change = getFile().getBoolean(getRoot() + this.getClass().getSimpleName());
		if (change) { // if event is active set false
			getFile().set(getRoot() + this.getClass().getSimpleName(), false);
			Core.configSettings.save();
		} else { // not active set true
			getFile().set(getRoot() + this.getClass().getSimpleName(), true);
			Core.configSettings.save();
		}

	}

	@Override
	public boolean isActive() {
		return active;
		// TODO Auto-generated method stub
		
	}

}
