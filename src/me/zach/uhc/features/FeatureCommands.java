package me.zach.uhc.features;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zach.uhc.Core;
import me.zach.uhc.features.events.ActiveFeatureEvent;
import me.zach.uhc.features.events.DeactiveFeatureEvent;

public class FeatureCommands implements CommandExecutor {

	private Core c;

	public FeatureCommands(Core c) {
		this.c = c;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if (!(sender instanceof Player))
			return true;

		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("feature")) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Invalid Usage: /feature list/on/off");
				return true;
			}
			switch (args[0]) {
			case "list":
				for (Feature f : c.featureManager.features) {
					p.sendMessage(ChatColor.BLUE + f.getFeature().getName() + ChatColor.RED +" isActive: " + f.isActive());
				}
				break;
			case "on":
				if (Features.isFeature(args[1])) {
					Feature f = c.featureManager.getFeature(Features.fromName(args[1]));
					ActiveFeatureEvent event = new ActiveFeatureEvent(p, f);
					Bukkit.getPluginManager().callEvent(event);
				} else {
					p.sendMessage(ChatColor.RED + "Unable to activate Feature: " + ChatColor.GOLD + args[1]);
				}
				break;
			case "off":
				if (Features.isFeature(args[1])) {
					Feature f = c.featureManager.getFeature(Features.fromName(args[1]));
					DeactiveFeatureEvent event = new DeactiveFeatureEvent(p, f);
					Bukkit.getPluginManager().callEvent(event);
				} else {
					p.sendMessage(ChatColor.RED + "Unable to deactivate Feature: " + ChatColor.GOLD + args[1]);
				}
				break;
			case "check":
				if (Features.isFeature(args[1])) {
					Feature f = c.featureManager.getFeature(Features.fromName(args[1]));
					if (f.isActive()) {
						p.sendMessage(ChatColor.GREEN + f.getFeature().getName() + " is currently active!");
					} else {
						p.sendMessage(ChatColor.RED + f.getFeature().getName() + " is currently NOT active!");
					}
				}
				break;
			case "buildGoldenHeads":
				List<String> yourList = new ArrayList<String>();
				yourList.add("&4Heals 4 hearts");
				yourList.add("&b&uProvides absorbtion");
				c.getConfig().set("feature.goldenhead.lore", yourList);
				c.saveConfig();
				break;
			default:
				p.sendMessage(ChatColor.RED + "Invalid Command Usage: /feature list, on, off");
				break;
			}
		}
		return false;
	}

}
