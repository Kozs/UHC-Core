package me.zach.uhc.content;

import me.zach.uhc.Core;

public class ContentManager {
	
	private Core c;
	
	public ContentManager(Core c) {
		this.c = c;
		
		c.getServer().getPluginManager().registerEvents(new AppleRates(), c);
	}
	

}
