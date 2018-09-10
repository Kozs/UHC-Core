package me.zach.uhc.util;

import java.util.Random;

import org.bukkit.entity.Player;

/**
 * 
 * @author Zachary Smith (2016) Helper Methods
 */
public class ExtraMethods {

	public static void removeItemHandByAmount(Player p, int amount) {
		if (p.getInventory().getItemInHand().getAmount() > amount) {
			p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - amount);
		} else {
			p.setItemInHand(null);
		}
	}

	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
