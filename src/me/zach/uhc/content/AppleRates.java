package me.zach.uhc.content;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.zach.uhc.ConfigConstants;
import me.zach.uhc.util.ExtraMethods;

public class AppleRates implements Listener {

	@EventHandler
	public void possibleAppleDrop(BlockBreakEvent event) {
		if (event.getBlock().getType() == Material.LEAVES || event.getBlock().getType() == Material.LEAVES_2) {
			int appleRates = ConfigConstants.APPLERATE;
			int number = ExtraMethods.randInt(1, 100);
			if (number <= appleRates) {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
			}
		}
	}

}
