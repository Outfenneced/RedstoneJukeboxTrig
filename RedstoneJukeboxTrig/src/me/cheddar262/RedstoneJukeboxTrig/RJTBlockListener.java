package me.cheddar262.RedstoneJukeboxTrig;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Jukebox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class RJTBlockListener implements Listener{
	public RedstoneJukeboxTrig plugin;
	RJTPlayerListener PListener = new RJTPlayerListener(plugin);
	private Material[] blockedCornerMaterials = {Material.REDSTONE_WIRE, Material.REDSTONE_TORCH_ON, Material.REDSTONE_TORCH_OFF, Material.WOOD_PLATE, Material.STONE_PLATE, Material.LEVER, Material.DIODE_BLOCK_OFF, Material.DIODE_BLOCK_ON};
	
	public RJTBlockListener (RedstoneJukeboxTrig instance) {
		plugin = instance;
	}
	@EventHandler
	public void onBlockRedstoneChange(BlockRedstoneEvent event){
		Block reds = (Block) event.getBlock();
		checkPowerJukebox(reds.getRelative(0,0,1));
		checkPowerJukebox(reds.getRelative(0,0,-1));
		checkPowerJukebox(reds.getRelative(1,0,0));
		checkPowerJukebox(reds.getRelative(-1,0,0));
	}
	private void checkPowerJukebox (Block block){
		if( block.getType() == Material.JUKEBOX){
			boolean redPower = redPowerChecker(block);
			Jukebox jukebox = (Jukebox) block.getState();
			String pos = jukebox.getWorld() + ", " + jukebox.getX() + ", " + jukebox.getY() + ", " + jukebox.getZ();
			if(redPower && (!block.isBlockPowered())){
				long StartTime = System.currentTimeMillis();
				Material playing = plugin.getPlaying(jukebox);
				if(playing != Material.AIR){
					if(plugin.posETime.containsKey(pos)){
						long eTimeTwo = plugin.posETime.get(pos).longValue();
						if(eTimeTwo <= (System.currentTimeMillis())){
							long EndTime = plugin.findEndTime(StartTime, playing);
							plugin.posETime.put(pos, EndTime);
							jukebox.setPlaying(playing);
						}
					}
					else{
						long EndTime = plugin.findEndTime(StartTime, playing);
						plugin.posETime.put(pos, EndTime);
						jukebox.setPlaying(playing);
					}
				}
			}
		}
	}
	private boolean redPowerChecker(Block block) {
		Material BlockNorthEast = block.getRelative(BlockFace.NORTH_EAST).getType();
		Material BlockNorthWest = block.getRelative(BlockFace.NORTH_WEST).getType();
		Material BlockSouthEast = block.getRelative(BlockFace.SOUTH_EAST).getType();
		Material BlockSouthWest = block.getRelative(BlockFace.SOUTH_WEST).getType();
		for (Material blockedMaterial: blockedCornerMaterials){
			if(BlockNorthEast == blockedMaterial || BlockNorthWest == blockedMaterial || BlockSouthEast == blockedMaterial || BlockSouthWest == blockedMaterial){
				return false;
			}
		}
		return true;
	}
}
