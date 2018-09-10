package me.zach.uhc.combatlogger;

import java.time.Instant;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

import me.zach.uhc.ConfigConstants;

public class TaggedUser {

	private Player player;

	private Location lastLoc;
	
	private long tagTime;

	public TaggedUser(Player p) {
		this.player = p;
		setTagTime(Instant.now().getEpochSecond());
		lastLoc = p.getLocation();
	}

	public boolean checkStatus() {
		long now = Instant.now().getEpochSecond();
		long difference = now - tagTime;
		if (difference > ConfigConstants.COMBATLOGGER_TAGTIME) {
			return true;
		}
		return false;
	}

	public boolean isOnline() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p == player) {
				return true;
			}
		}
		return false;
	}
	
	public void spawnWolf() {
		Wolf w = null;
		w = (Wolf) player.getWorld().spawnCreature(lastLoc, EntityType.WOLF);
		w.setTamed(true);
		w.setSitting(true);
		w.setCustomName(player.getDisplayName() + "'s Logger Dogger");
	}

	public Player getPlayer() {
		return player;
	}

	public long getTagTime() {
		return tagTime;
	}

	public void setTagTime(long tagTime) {
		this.tagTime = tagTime;
	}

}
