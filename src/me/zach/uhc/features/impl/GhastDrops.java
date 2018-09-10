package me.zach.uhc.features.impl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.zach.uhc.Core;
import me.zach.uhc.features.Feature;
import me.zach.uhc.features.Features;

public class GhastDrops extends Feature {

	boolean active = Core.configSettings.getFeatures().getBoolean("features." + this.getClass().getSimpleName());
	
	public GhastDrops(Core c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		Bukkit.broadcast(ChatColor.AQUA + "Ghasts will now drop " + ChatColor.GOLD + " GOLD", "core.staff");
		activateListeners();
		active = true;
	}

	@Override
	public void end() {
		Bukkit.broadcast(ChatColor.AQUA + "Ghasts will no longer drop " + ChatColor.GOLD + " GOLD", "core.staff");
		deactiveListeners();
		active = false;
	}

	@EventHandler
	public void ghastDeathEvent(EntityDeathEvent event) {
		if (event.getEntityType() == EntityType.GHAST) {
			event.getDrops().clear();
			Location dropSpot = event.getEntity().getLocation();
			event.getEntity().getWorld().dropItemNaturally(dropSpot, new ItemStack(Material.GOLD_INGOT, con.GHAST_DROP_AMT));
		}
	}
	
	@Override
	public void activateListeners() {
		getCore().getServer().getPluginManager().registerEvents(this, getCore());
		
	}

	@Override
	public void deactiveListeners() {
		EntityDeathEvent.getHandlerList().unregister(this);
	}

	@Override
	public Features getFeature() {
		// TODO Auto-generated method stub
		return Features.GhastDrops;
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
