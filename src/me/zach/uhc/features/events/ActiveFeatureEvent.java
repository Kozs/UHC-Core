package me.zach.uhc.features.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.zach.uhc.features.Feature;

public class ActiveFeatureEvent extends Event {
	
	private Feature feature;
	private Player player;
	
	public ActiveFeatureEvent(Player sender, Feature f) {
		this.player = sender;
		this.feature = f;
	}
	
	private static final HandlerList handlers = new HandlerList();

	public Player getActivator() {
		return player;
	}
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
	public Feature getFeature() {
		return feature;
	}

}
