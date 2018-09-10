package me.zach.uhc.features.impl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import me.zach.uhc.Core;
import me.zach.uhc.features.Feature;
import me.zach.uhc.features.Features;

public class EpearlDamage extends Feature {

	private boolean active = Core.configSettings.getFeatures().getBoolean("features.EpearlDamage");

	public EpearlDamage(Core c) {
		super(c);
	}

	@Override
	public void run() {
		activateListeners();
		setActive();
	}

	@Override
	public void end() {
		deactiveListeners();
		setActive();
	}

	@Override
	public void activateListeners() {
		Bukkit.broadcast(ChatColor.GOLD + "[Feature] " + ChatColor.DARK_GRAY + "EnderPearlDamage Disabled!", "core.staff");
		getCore().getServer().getPluginManager().registerEvents(this, getCore());
		active = true;
	}

	@EventHandler
	public void onProjectileHitEvent(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		TeleportCause cause = event.getCause();
		Location to = event.getTo();

		if (cause == TeleportCause.ENDER_PEARL) {
			event.setCancelled(true);
			player.teleport(to);
		}
	}

	@Override
	public void deactiveListeners() {
		Bukkit.broadcast(ChatColor.GOLD + "[Feature] " + ChatColor.DARK_GRAY + "EnderPearlDamage Enabled!", "core.staff");
		PlayerTeleportEvent.getHandlerList().unregister(this);
		active = false;
	}

	@Override
	public Features getFeature() {
		return Features.EpearlDamage;
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
