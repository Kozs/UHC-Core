package me.zach.uhc.content;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerSpread {

	public PlayerSpread() {

	}

	public void teleportPlayers(World world, int x, int z) {
		for (Player p: Bukkit.getOnlinePlayers()) {

			// if e is a player
			World w = world;

			double xRandom = ((Math.random() * x)) - (x/2);
			double zRandom = ((Math.random() * z)) - (z/2);
			double yRandom = w.getHighestBlockYAt((int) xRandom, (int) zRandom);
			Location randomLocation = new Location(w, xRandom, yRandom, zRandom);
			p.teleport(randomLocation);
			p.sendMessage("You've been teleported to a random location!");
			// Check if there are any players near the location here. If so,
			// keep creating a location until there aren't any nearby players
			if (p.getNearbyEntities(50, 50, 50).size() > 0) {
				p.teleport(randomLocation);
				p.sendMessage("You've been re-teleported to a different location!");
			} else {
				return;
			}

		}
	}
}
