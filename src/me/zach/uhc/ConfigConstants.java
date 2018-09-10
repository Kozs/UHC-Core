package me.zach.uhc;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigConstants {

	public static String CONFIG_GHAST_DROP_AMT = "feature.ghastdrops.ghastGoldDropAmount";
	public static String CONFIG_GOLDENHEAD_LORE = "feature.goldenheads.goldenHeadsLore";
	public static String CONFIG_GOLDENHEAD_ABSORBTION = "feature.goldenheads.absorbtiontime.seconds";
	public static String CONFIG_GOLDENHEAD_ABSORBTION_HEARTS = "feature.goldenheads.absorbtion.healthGain";
	public static String CONFIG_GOLDENHEAD_ABSORBTION_LEVEL = "feature.goldenheads.absorbtion.PotionLevel";
	public static String CONFIG_COMBATLOGGER_TAGTIME = "combatlogger.tagtime";
	public static String CONFIG_CONTENT_FEEDALL = "content.feed.msg.feedAll";
	public static String CONFIG_CONTENT_FEEDPLAYER = "content.feed.msg.feedPlayer";
	public static String CONFIG_CONTENT_FEEDINVALID = "content.feed.msg.feedInvalid";
	public static String CONFIG_NOPERM_ERR_MSG = "uhc.msg.noperm";
	public static String CONFIG_UHC_MSG_PREFIX = "uhc.msg.prefix";
	public static String CONFIG_CONTENT_HEALALL = "content.heal.msg.healAll";
	public static String CONFIG_CONTENT_HEALPLAYER = "content.heal.msg.healPlayer";
	public static String CONFIG_CONTNET_HEALINVALID = "content.heal.msg.healInvalid";
	public static String CONFIG_CONTENT_EXP_CLEARALL = "content.exp.clear.msg.clearAll";
	public static String CONFIG_CONTENT_EXP_CLEARPLAYER = "content.exp.clear.msg.clearPlayer";
	public static String CONFIG_CONTENT_EXP_CLEARINVALID = "content.exp.clear.msg.clearInvalid";
	public static String CONFIG_CONTENT_APPLE_RATE = "content.apple.rate";

	public static int GHAST_DROP_AMT;
	public static List<String> GOLDENHEADLORE;
	public static int ABSORBTIONTIME;
	public static int ABSORBTIONHEARTS;
	public static int ABSORBTIONPOTLEVEL;
	public static int COMBATLOGGER_TAGTIME;
	public static int APPLERATE;
	public static String FEEDALLMSG, FEEDPLAYERMSG, FEEDINVALIDMSG, NOPERMMSG, COREPREFIX, HEALALLMSG, HEALPLAYERMSG,
			HEALINVALIDMSG, EXPCLEARALLMSG, EXPCLEARPLAYERMSG, EXPCLEARINVALIDMSG;

	private FileConfiguration serverConfig;

	private Core c;

	public static ConfigConstants getConstants;

	public ConfigConstants(Core c) {
		this.c = c;
		serverConfig = c.getConfig();
		loadConfigurationVars();
		ConfigConstants.getConstants = this;
	}

	// Loads Config Vars to Constants
	public void loadConfigurationVars() {
		GHAST_DROP_AMT = serverConfig.getInt(CONFIG_GHAST_DROP_AMT);
		GOLDENHEADLORE = (List<String>) serverConfig.getList(CONFIG_GOLDENHEAD_LORE);
		ABSORBTIONTIME = serverConfig.getInt(CONFIG_GOLDENHEAD_ABSORBTION);
		ABSORBTIONHEARTS = serverConfig.getInt(CONFIG_GOLDENHEAD_ABSORBTION_HEARTS);
		ABSORBTIONPOTLEVEL = serverConfig.getInt(CONFIG_GOLDENHEAD_ABSORBTION_LEVEL);
		COMBATLOGGER_TAGTIME = serverConfig.getInt(CONFIG_COMBATLOGGER_TAGTIME);
		FEEDALLMSG = serverConfig.getString(CONFIG_CONTENT_FEEDALL);
		FEEDPLAYERMSG = serverConfig.getString(CONFIG_CONTENT_FEEDPLAYER);
		FEEDINVALIDMSG = serverConfig.getString(CONFIG_CONTENT_FEEDINVALID);
		NOPERMMSG = serverConfig.getString(CONFIG_NOPERM_ERR_MSG);
		COREPREFIX = serverConfig.getString(CONFIG_UHC_MSG_PREFIX);
		HEALALLMSG = serverConfig.getString(CONFIG_CONTENT_HEALALL);
		HEALPLAYERMSG = serverConfig.getString(CONFIG_CONTENT_HEALPLAYER);
		HEALINVALIDMSG = serverConfig.getString(CONFIG_CONTNET_HEALINVALID);
		EXPCLEARINVALIDMSG = serverConfig.getString(CONFIG_CONTENT_EXP_CLEARINVALID);
		EXPCLEARPLAYERMSG = serverConfig.getString(CONFIG_CONTENT_EXP_CLEARPLAYER);
		EXPCLEARALLMSG = serverConfig.getString(CONFIG_CONTENT_EXP_CLEARALL);
		APPLERATE = serverConfig.getInt(CONFIG_CONTENT_APPLE_RATE);
	}

	public void updateConfigValue(String key, Object value) {
		serverConfig.set(key, value);
		c.saveConfig();
	}

	public ConfigConstants getGetConstants() {
		return getConstants;
	}

}
