package me.zach.uhc.features;

public enum Features {
	DeathLightning("DeathLightning"),
	EpearlDamage("EpearlDamage"),
	GhastDrops("GhastDrops"),
	PlayerHeads("PlayerHeads"),
	DisableNatRegen("DisNaturalRegen"),
	GoldenHeads("GoldenHeads"),
	CaveSpiderPOS("CaveSpiderPoison"),
	WitchSpawns("WitchSpawns"),
	PotionsNerf("PotionNerf"),
	NetherPortals("NetherPortals");
	
	String name;
	
	Features(String name) {
		this.name = name;
	}
	
	public static boolean isFeature(String guess) {
		for(Features type: Features.values()) {
			if (type.getName().toLowerCase().indexOf(guess.toLowerCase()) != -1) {
				return true;
			}
		}
		return false;
	}

    public static Features fromName(String name) {
        for (Features type : Features.values()) if (type.toString().equalsIgnoreCase(name)) return type;
        return null;
    }
	public String getName() {
		return name;
	}
}
