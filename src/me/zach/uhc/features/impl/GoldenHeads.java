package me.zach.uhc.features.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.zach.uhc.Core;
import me.zach.uhc.features.Feature;
import me.zach.uhc.features.Features;
import me.zach.uhc.util.ExtraMethods;

public class GoldenHeads extends Feature {

	boolean active = Core.configSettings.getFeatures().getBoolean("features." + this.getClass().getSimpleName());

	ShapedRecipe recipe;

	public GoldenHeads(Core c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		activateListeners();
		active = true;
	}

	@Override
	public void end() {
		deactiveListeners();
		removeDefaultCraft(recipe);
		active = false;
	}

	@Override
	public void activateListeners() {
		getPluginManager().registerEvents(this, getCore());
		initRecipie();
	}

	@Override
	public void deactiveListeners() {
		PrepareItemCraftEvent.getHandlerList().unregister(this);
	}

	@Override
	public Features getFeature() {
		return Features.GoldenHeads;
	}

	@Override
	public void setActive() {
		boolean change = getFile().getBoolean(getRoot() + this.getClass().getSimpleName());
		if (change) { // if event is active set false
			getFile().set(getRoot() + this.getClass().getSimpleName(), false);
			Core.configSettings.save();
		} else { // not active set true
			getFile().set(getRoot() + this.getClass().getSimpleName(), true);
			Core.configSettings.save();
		}
	}

	@Override
	public boolean isActive() {
		return active;
	}

	public ItemStack getGoldenHead(String playerName) {
		ItemStack build = new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1);
		ItemMeta headMeta = build.getItemMeta();
		headMeta.setDisplayName(ChatColor.GOLD + playerName + "'s Golden Head");
		List<String> newLore = new ArrayList<String>();
		for (String s : con.GOLDENHEADLORE) {
			newLore.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		headMeta.setLore(newLore);
		build.setItemMeta(headMeta);
		return build;
	}

	@EventHandler
	public void consume(PlayerItemConsumeEvent event) {
		ItemStack target = event.getItem();
		if (target.getType() == Material.GOLDEN_APPLE) {
			if (target.hasItemMeta()) {
				if (target.getItemMeta().hasLore()) {
					event.setCancelled(true);
					Player p = event.getPlayer();
					ExtraMethods.removeItemHandByAmount(p, 1);
					p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, con.ABSORBTIONTIME * 20,
							con.ABSORBTIONPOTLEVEL));
					double health = ((Damageable) p).getHealth();
					double maxHealth = ((Damageable)p).getMaxHealth();
					if (health == maxHealth)
						return;
					if (health + con.ABSORBTIONHEARTS > maxHealth) {
						p.setHealth(maxHealth);
					} else {
						p.setHealth(health + con.ABSORBTIONHEARTS);
					}
				}
			}
		}
	}

	@EventHandler
	public void on(PrepareItemCraftEvent event) {
		if (event.getRecipe().getResult().getType() != Material.GOLDEN_APPLE)
			return;

		final ItemStack centre = event.getInventory().getMatrix()[4];

		if (centre == null || centre.getType() != Material.SKULL_ITEM)
			return;

		final SkullMeta meta = (SkullMeta) centre.getItemMeta();
		final ItemStack goldenHeadItem = getGoldenHead(meta.hasOwner() ? meta.getOwner() : "Manually Created");
		event.getInventory().setResult(goldenHeadItem);
	}

	private void initRecipie() {
		recipe = new ShapedRecipe(new ItemStack(Material.GOLDEN_APPLE, 1));

		// Here we will set the places. E and S can represent anything, and the
		// letters can be anything. Beware; this is case sensitive.
		recipe.shape("GGG", "GHG", "GGG");

		// Set what the letters represent.
		// E = Emerald, S = Stick
		// CraftItemEvent
		recipe.setIngredient('G', Material.GOLD_INGOT);
		recipe.setIngredient('H', Material.SKULL_ITEM, 3);

		// Finally, add the recipe to the bukkit recipes
		Bukkit.addRecipe(recipe);
	}

	/**
	 * Remove a recipe from minecraft. Works for vanilla and custom recipes.
	 *
	 * @param item
	 *            The ItemStack (result) of the recipe you want to remove
	 */
	public void removeDefaultCraft(Recipe rec) {
		// First, All the registered recipes are stored in a List, so we will
		// get the iterator so we can search through it.
		Iterator<Recipe> it = Bukkit.getServer().recipeIterator();

		// Now we will loop through the list from start to end
		while (it.hasNext()) {

			if (it.next() == rec) {
				it.remove();
			}

		}
	}

}
