package me.cheddar262.RedstoneJukeboxTrig;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Jukebox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RJTPlayerListener implements Listener{
	public RedstoneJukeboxTrig plugin;
	long StartTime;
	
	
	public RJTPlayerListener(RedstoneJukeboxTrig instance) {
		plugin = instance;
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) { //Made sure it's a right click	
			Block block = event.getClickedBlock();
			if(block.getType() == Material.JUKEBOX) { //Made sure it's a right click on a jukebox
				Jukebox juke = (Jukebox) block.getState();
				if(plugin.getPlaying(juke) == Material.AIR){ //Make sure the player is inserting and not taking out
					ItemStack hand = event.getPlayer().getItemInHand();
					if(hand.getType() == Material.GREEN_RECORD || hand.getType() == Material.GOLD_RECORD || hand.getType() == Material.RECORD_3 || hand.getType() == Material.RECORD_4 || hand.getType() == Material.RECORD_5 || hand.getType() == Material.RECORD_6 || hand.getType() == Material.RECORD_7 || hand.getType() == Material.RECORD_8 || hand.getType() == Material.RECORD_9 || hand.getType() == Material.RECORD_10 || hand.getType() == Material.RECORD_11) //Made sure it's putting in a record and not taking it out. Also made sure it's a record that was inserted.
					{
						Material item = event.getMaterial();
						String pos = block.getWorld() + ", " + block.getX() + ", " + block.getY() + ", " + block.getZ();
						StartTime = System.currentTimeMillis();
						long endTime = plugin.findEndTime(StartTime, item);
						plugin.posETime.put(pos, endTime);
					}
				}
			}
		}
	}
}