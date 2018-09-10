package me.zach.uhc.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.zach.uhc.ConfigConstants;

/**
 * 
 * @author Zachary Smith (2016) Each user will be assigned this
 */

public class UHCPlayer {

	private Player player;

	public UHCPlayer(Player player) {
		this.player = player; //declare player var 
	}

	public void sM(String msg) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigConstants.COREPREFIX + msg));
	}
	
	public void clearExperience() {
		player.setExp(0);
	}
	
	public void removeExpLevel(float amount) {
		player.setExp(player.getExp() - amount);
	}
	
	public Player getPlayer() {
		return player;
	}
}
