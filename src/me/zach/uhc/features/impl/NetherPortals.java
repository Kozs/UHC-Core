package me.zach.uhc.features.impl;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import me.zach.uhc.Core;
import me.zach.uhc.features.Feature;
import me.zach.uhc.features.Features;

public class NetherPortals extends Feature {

	boolean active = Core.configSettings.getFeatures().getBoolean("features." + this.getClass().getSimpleName());
	
	public NetherPortals(Core c) {
		super(c);
		// TODO Auto-generated constructor stub
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
	public void netherPortal(PlayerPortalEvent event) {
		TeleportCause cause = event.getCause();
		if (cause == TeleportCause.NETHER_PORTAL) {
			event.setCancelled(true);
		}
	}
	
	@Override
	public void deactiveListeners() {
		PlayerPortalEvent.getHandlerList().unregister(this);
		
	}

	@Override
	public Features getFeature() {
		// TODO Auto-generated method stub
		return Features.NetherPortals;
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
	}

}
