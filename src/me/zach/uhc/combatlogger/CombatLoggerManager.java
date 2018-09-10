package me.zach.uhc.combatlogger;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

/**
 * 
 * @author Zachary Smith (2016)
 * Will carry all of the 'tagged for combat' users!
 */

public class CombatLoggerManager {

	public static List<Player> tagged = new ArrayList<Player>();
	
	public CombatLoggerManager() {
		
	}
	
	public static boolean containsPlayer(Player p) {
		return tagged.contains(p);
	}
	
}
