package me.zach.uhc.content.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import com.sk89q.worldedit.LocalPlayer;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.function.pattern.Pattern;

import me.zach.uhc.ConfigConstants;
import me.zach.uhc.Core;
import me.zach.uhc.content.PlayerSpread;
import me.zach.uhc.content.WorldBorderHandler;
import me.zach.uhc.player.UHCPlayer;

public class ContentCommands implements CommandExecutor {

	private Core c;

	public ContentCommands(Core c) {
		this.c = c;
		c.getCommand("content").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if (cmd.getName().equalsIgnoreCase("content")) {
			Player playa = (Player) sender;
			UHCPlayer cmdSender = Core.uhcPM.getUHCPlayer((Player) sender);
			double senderMaxHealth = ((Damageable) (Player) sender).getMaxHealth();
			if (cmdSender.getPlayer().hasPermission("uhc.content.cmds")) {
				if (args.length == 0) {
					cmdSender.sM("&aPlease refer to helpdocs for Command usage!");
					return true;
				}
				switch (args[0]) {
				// all content commands
				case "feed":
					if (args.length == 1) {
						cmdSender.sM("&aYou've fed yourself!");
						cmdSender.getPlayer().setFoodLevel(20);
						return true;
					}
					if (args[1].equalsIgnoreCase("*")) {
						for (Player p : Bukkit.getOnlinePlayers()) {
							UHCPlayer uhcP = Core.uhcPM.getUHCPlayer(p);
							uhcP.getPlayer().setFoodLevel(20);
							uhcP.sM(ConfigConstants.FEEDALLMSG);
						}
						return true;
					}
					Player ptarget = Bukkit.getPlayer(args[1]);
					if (ptarget != null) {
						UHCPlayer target = Core.uhcPM.getUHCPlayer(ptarget);
						target.getPlayer().setFoodLevel(20);
						target.sM(ConfigConstants.FEEDPLAYERMSG);
						return true;
					} else {
						cmdSender.sM(ConfigConstants.FEEDINVALIDMSG);
					}
					break;
				case "heal":
					if (args.length == 1) {
						cmdSender.sM("&4You've healed yourself!");
						cmdSender.getPlayer().setHealth(senderMaxHealth);
						return true;
					}
					if (args[1].equalsIgnoreCase("*")) {
						for (Player p : Bukkit.getOnlinePlayers()) {
							UHCPlayer uhcP = Core.uhcPM.getUHCPlayer(p);
							uhcP.sM(ConfigConstants.HEALALLMSG);
						}
						return true;
					}
					Player pTarget = Bukkit.getPlayer(args[1]);
					if (pTarget != null) {
						cmdSender.sM(ConfigConstants.HEALPLAYERMSG);
					} else {
						cmdSender.sM(ConfigConstants.HEALINVALIDMSG);
					}
					break;
				case "exp":
					if (args[1].equalsIgnoreCase("clear")) {
						Player p = null;
						if (args.length == 1) {
							cmdSender.clearExperience();
							cmdSender.sM("&aYou have cleared your total experience!");
							return true;
						}
						p = Bukkit.getPlayer(args[2]);
						if (args[2].equalsIgnoreCase("*")) {
							for (Player pl : Bukkit.getOnlinePlayers()) {
								UHCPlayer target = Core.uhcPM.getUHCPlayer(pl);
								target.clearExperience();
								cmdSender.sM(ConfigConstants.EXPCLEARALLMSG);
								return true;
							}
						} else if (p != null) {
							UHCPlayer target = Core.uhcPM.getUHCPlayer(p);
							target.clearExperience();
							target.sM(ConfigConstants.EXPCLEARPLAYERMSG);
						} else {
							cmdSender.sM(ConfigConstants.EXPCLEARINVALIDMSG);
						}
						
					}
					break;
				case "applerates":
					if (args[1].equalsIgnoreCase("check")) {
						cmdSender.sM("&cApple Rates: &a"+ConfigConstants.APPLERATE+"%");
						break;
					}
					if (args[1].equalsIgnoreCase("set")) {
						int newValue = Integer.parseInt(args[2]);
						Core.configHandler.updateConfigValue(ConfigConstants.CONFIG_CONTENT_APPLE_RATE, newValue);
						cmdSender.sM("&4You've changed the AppleRate drop percentage to: " + newValue + "%!");
						break;
					}
					break;
				case "worldborder":
					WorldBorderHandler wbh = c.wb;
					World w = cmdSender.getPlayer().getWorld();
					Location loc = cmdSender.getPlayer().getLocation();
					try {
						wbh.generateWalls(playa, new Location(playa.getWorld(), -51, 70, 247), new Location(playa.getWorld(), -34, 71, 224));
					} catch (MaxChangedBlocksException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "sca":
					World ws = Bukkit.getWorld(args[1]);
					if (ws == null) {
						cmdSender.sM("&cErr: World is null");
						break;
					}
					PlayerSpread ps = new PlayerSpread();
					ps.teleportPlayers(ws, Integer.parseInt(args[2]), Integer.parseInt(args[3]));
					break;

				default:
					cmdSender.sM("&a/content feed/heal/exp");
					break;
				}
			} else {
				cmdSender.sM(ConfigConstants.NOPERMMSG);
				return true;
			}
		}
		return false;
	}

}
