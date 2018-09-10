package me.zach.uhc.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.zach.uhc.Core;

/**
 * 
 * @author Zachary Smith (2016)
 * This class will add the user that joined the server to the UHCPlayerManager Map and any future OBJECT events needed
 */
public class UHCPlayerListener implements Listener {
	
	@EventHandler
	public void onJoinRegistration(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		if(Core.uhcPM.getUHCPlayer(p) == null) {
			Core.log.info("Added " + p.getName() + " to UHCPlayer Map!");
			Core.uhcPM.uhcPlayerMap.put(p, new UHCPlayer(p));
		}
	}

}
