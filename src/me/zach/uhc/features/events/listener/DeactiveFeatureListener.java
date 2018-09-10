package me.zach.uhc.features.events.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.zach.uhc.features.Feature;
import me.zach.uhc.features.events.DeactiveFeatureEvent;

public class DeactiveFeatureListener implements Listener {

	@EventHandler
	public void deactiveFeature(DeactiveFeatureEvent event) {
		Feature f = event.getFeature();
		if (f.isActive()) {
			f.end();
			f.setActive();
		} else {
			event.getDeactivator().sendMessage(f.getFeature().getName() + " is already deactivated!");
		}
	}

}
