package me.zach.uhc.features.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.zach.uhc.features.Feature;

public class DeactiveFeatureEvent extends Event {

	private Feature feature;
	private Player player;
	
	public DeactiveFeatureEvent(Player p, Feature feature) {
		this.player = p;
		this.feature = feature;
	}
	
	public Player getDeactivator() {
		return player;
	}
	
	public Feature getFeature() {
		return feature;
	}
	
	private static final HandlerList handlers = new HandlerList();
	
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
