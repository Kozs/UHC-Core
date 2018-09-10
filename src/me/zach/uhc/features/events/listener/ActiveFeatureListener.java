package me.zach.uhc.features.events.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.zach.uhc.features.Feature;
import me.zach.uhc.features.events.ActiveFeatureEvent;

public class ActiveFeatureListener implements Listener {

	@EventHandler
	public void activateFeature(ActiveFeatureEvent event) {
		Feature f = event.getFeature();
		if (!f.isActive()) {
			f.run();
			f.setActive();
		} else {
			event.getActivator().sendMessage(f.getFeature().getName() + " is already active!");
		}
	}

}
