package me.zach.uhc.features.impl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.zach.uhc.Core;
import me.zach.uhc.features.Feature;
import me.zach.uhc.features.Features;

public class DeathLightning extends Feature {

	boolean active = Core.configSettings.getFeatures().getBoolean("features." + this.getClass().getSimpleName());

	public DeathLightning(Core c) {
		super(c);
	}

	@Override
	public void activateListeners() {
		getCore().getServer().getPluginManager().registerEvents(this, getCore());
	}

	@Override
	public void deactiveListeners() {
		EntityDamageByEntityEvent.getHandlerList().unregister(this);
		PlayerDeathEvent.getHandlerList().unregister(this);
	}

	@EventHandler
	public void lightningdamage(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof LightningStrike) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void handleLightingDeath(PlayerDeathEvent event) {
		Player victim = event.getEntity();
		Location deathLoc = victim.getLocation();
		World w = deathLoc.getWorld();
		w.strikeLightningEffect(deathLoc);
	}

	@Override
	public Features getFeature() {
		return Features.DeathLightning;
	}

	@Override
	public void run() {
		Bukkit.broadcast(ChatColor.AQUA + "Lightning will now fall on Death!", "core.staff");
		activateListeners();
		active = true;
	}

	@Override
	public void end() {
		Bukkit.broadcast(ChatColor.AQUA + "Lightning has concluded!", "core.staff");
		deactiveListeners();
		active = false;
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
