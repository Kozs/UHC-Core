package me.zach.uhc.content;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldguard.protection.managers.RegionManager;

import me.zach.uhc.Core;

public class WorldBorderHandler {

	private EditSession editSession;
	private WorldEdit we;
	private Core c;

	public WorldBorderHandler(Core c) {
		this.c = c;
		try {
			we = c.getWorldGuard().getWorldEdit().getWorldEdit();
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setBlocks() {
		// editSession.makeCuboidWalls(region, block)
	}

	public void getRegion(World w, Location loc) {
		RegionManager rm = c.getWorldGuard().getRegionManager(w);
		Core.log.info(rm.getApplicableRegions(loc).getRegions().iterator().next().getId());

		editSession.enableQueue();
		Core.log.info("Queue Enabled? " + editSession.isQueueEnabled());
	}

	@SuppressWarnings("deprecation")
	public void generateWalls(Player playe, Location Location1, Location Location2) throws MaxChangedBlocksException {
		CuboidSelection selection = new CuboidSelection(playe.getWorld(), Location1, Location2);
		World w = playe.getWorld();
		BaseBlock block = new BaseBlock(1);
		Region region = null;
		editSession = new EditSession(new BukkitWorld(w), we.getConfiguration().maxChangeLimit);
		try {
			region = selection.getRegionSelector().getRegion();
			Bukkit.broadcastMessage("Region SELECTED: " + region.getArea());
		} catch (IncompleteRegionException e) {
			e.printStackTrace();
		}
		Vector min = region.getMinimumPoint();
		Vector max = region.getMaximumPoint();

		int minX = min.getBlockX();
		int minZ = min.getBlockZ();
		int minY = w.getHighestBlockYAt(minX, minZ);
		int maxX = max.getBlockX();
		int maxZ = max.getBlockZ();
		int maxY = w.getHighestBlockYAt(maxX, maxZ);

		for (int x = minX; x <= maxX; ++x) {
			for (int y = minY; y <= maxY; ++y) {
				if (editSession.setBlock(new Vector(x, y, minZ), block)) {
				}
				if (editSession.setBlock(new Vector(x, y, maxZ), block)) {
				}
			}
		}

		for (int y = minY; y <= maxY; ++y) {
			for (int z = minZ; z <= maxZ; ++z) {
				if (editSession.setBlock(new Vector(minX, y, z), block)) {

				}
				if (editSession.setBlock(new Vector(maxX, y, z), block)) {

				}
			}
		}
	}
	
    public static WorldEditPlugin getWorldEdit(){
        if(Core.getInstance().getServer().getPluginManager().getPlugin("WorldEdit") != null){
            if(!(Core.getInstance().getServer().getPluginManager().getPlugin("WorldEdit") instanceof  WorldEditPlugin)){
                return (WorldEditPlugin) Core.getInstance().getServer().getPluginManager().getPlugin("WorldEdit");
            }else{
                return null;
            }
 
        }else{
            return null;
        }
 
    }

	public EditSession getEditSession() {
		return editSession;
	}
}
