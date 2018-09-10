package me.zach.uhc.features.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.zach.uhc.Core;
import me.zach.uhc.features.Feature;
import me.zach.uhc.features.Features;

public class PlayerHeads extends Feature {

	boolean active = Core.configSettings.getFeatures().getBoolean("features." + this.getClass().getSimpleName());

	public PlayerHeads(Core c) {
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
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (event.getEntity().getKiller() instanceof Player) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta meta = (SkullMeta) skull.getItemMeta();
			meta.setOwner(event.getEntity().getName());
			skull.setItemMeta(meta);
			event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), skull);
		}
	}

	@Override
	public void deactiveListeners() {
		PlayerDeathEvent.getHandlerList().unregister(this);
	}

	@Override
	public Features getFeature() {
		// TODO Auto-generated method stub
		return Features.PlayerHeads;
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
