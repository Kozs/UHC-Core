package me.zach.uhc.player;

import java.util.HashMap;

import org.bukkit.entity.Player;

import me.zach.uhc.Core;

/**
 * 
 * @author Zachary Smith (2016)
 * Class is designed to handle player tasks
 */

public class UHCPlayerManager {
	
	public HashMap<Player, UHCPlayer> uhcPlayerMap = new HashMap<Player, UHCPlayer>();
	
	private Core c;
	
	public UHCPlayerManager(Core c) {
		this.c = c;
		
		//register playerListener listener :P
		c.getServer().getPluginManager().registerEvents(new UHCPlayerListener(), c);
	}
	
	public UHCPlayer getUHCPlayer(Player p) {
		if (uhcPlayerMap.get(p) == null) {
			uhcPlayerMap.put(p, new UHCPlayer(p));
		}
		return uhcPlayerMap.get(p);
	}

}
