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
			if(redPower && (!block.isBlockPowered())){
				Jukebox jukebox = (Jukebox) block.getState();
				String pos = jukebox.getWorld() + ", " + jukebox.getX() + ", " + jukebox.getY() + ", " + jukebox.getZ();
				long StartTime = System.currentTimeMillis();
				Material playing = getPlaying(jukebox);
				if(playing != Material.AIR){
					if(RJTPlayerListener.posETime.containsKey(pos)){
						long eTimeTwo = RJTPlayerListener.posETime.get(pos).longValue();
						if(eTimeTwo <= (System.currentTimeMillis())){
							long EndTime = RJTPlayerListener.findEndTime(StartTime, playing);
							RJTPlayerListener.posETime.put(pos, EndTime);
							jukebox.setPlaying(playing);
						}
					}
					else{
						long EndTime = RJTPlayerListener.findEndTime(StartTime, playing);
						RJTPlayerListener.posETime.put(pos, EndTime);
						jukebox.setPlaying(playing);
					}
				}
			}
		}
	}
	private boolean redPowerChecker(Block block) {
		Material BlockNorthEastType = block.getRelative(BlockFace.NORTH_EAST).getType();
		Material BlockNorthWestType = block.getRelative(BlockFace.NORTH_WEST).getType();
		Material BlockSouthEastType = block.getRelative(BlockFace.SOUTH_EAST).getType();
		Material BlockSouthWestType = block.getRelative(BlockFace.SOUTH_WEST).getType();
		boolean NE = false;
		boolean NW = false;
		boolean SE = false;
		boolean SW = false;
		if((BlockNorthEastType != Material.REDSTONE_WIRE) && (BlockNorthEastType != Material.REDSTONE_TORCH_ON) && (BlockNorthEastType != Material.REDSTONE_TORCH_OFF) && (BlockNorthEastType != Material.WOOD_PLATE) && (BlockNorthEastType != Material.STONE_PLATE) && (BlockNorthEastType != Material.LEVER) && (BlockNorthEastType != Material.DIODE_BLOCK_OFF) && (BlockNorthEastType != Material.DIODE_BLOCK_ON)) {
			NE = true;
		}
		if((BlockNorthWestType != Material.REDSTONE_WIRE) && (BlockNorthWestType != Material.REDSTONE_TORCH_ON) && (BlockNorthWestType != Material.REDSTONE_TORCH_OFF) && (BlockNorthWestType != Material.WOOD_PLATE) && (BlockNorthWestType != Material.STONE_PLATE) && (BlockNorthWestType != Material.LEVER) && (BlockNorthWestType != Material.DIODE_BLOCK_OFF) && (BlockNorthWestType != Material.DIODE_BLOCK_ON)) {
			NW = true;
		}
		if((BlockSouthEastType != Material.REDSTONE_WIRE) && (BlockSouthEastType != Material.REDSTONE_TORCH_ON) && (BlockSouthEastType != Material.REDSTONE_TORCH_OFF) && (BlockSouthEastType != Material.WOOD_PLATE) && (BlockSouthEastType != Material.STONE_PLATE) && (BlockSouthEastType != Material.LEVER) && (BlockSouthEastType != Material.DIODE_BLOCK_OFF) && (BlockSouthEastType != Material.DIODE_BLOCK_ON)) {
			SE = true;
		}
		if((BlockSouthWestType != Material.REDSTONE_WIRE) && (BlockSouthWestType != Material.REDSTONE_TORCH_ON) && (BlockSouthWestType != Material.REDSTONE_TORCH_OFF) && (BlockSouthWestType != Material.WOOD_PLATE) && (BlockSouthWestType != Material.STONE_PLATE) && (BlockSouthWestType != Material.LEVER) && (BlockSouthWestType != Material.DIODE_BLOCK_OFF) && (BlockSouthWestType != Material.DIODE_BLOCK_ON)) {
			SW = true;
		}
		if(NE && NW && SE && SW){
			return true;
		} else{
			return false;
		}
	}
	public Material getPlaying(Jukebox jb) {
	    try {
	        jb.getPlaying();
	        return jb.getPlaying();
	    } catch (Exception e) {
	        return Material.AIR;
	    }
	}
}
