package me.zach.uhc.combatlogger.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class CombatTagger implements Listener {

	public void dmg(final EntityDamageEvent event) {
		Entity e = event.getEntity();
		if (e instanceof Player) {
			
		}
	}

}
