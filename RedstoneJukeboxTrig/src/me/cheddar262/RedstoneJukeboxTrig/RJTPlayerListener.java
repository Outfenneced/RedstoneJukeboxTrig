package me.cheddar262.RedstoneJukeboxTrig;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
	public static Map<String, Long> posETime = Collections.synchronizedMap(new HashMap<String, Long> ());
	long StartTime;
	
	static long[] recordTimes = { //(Minutes * 60) + seconds
			((3*60)+3), //13
			((3*60)+6), //cat
			((5*60)+12), //blocks
			((3*60)+5), //chirp
			((2*60)+54), //far
			((3*60)+17), //mall
			((1*60)+37), //mellohi
			((2*60)+30), //stal
			((3*60)+9), //strad
			((4*60)+10), //ward
			((1*60)+10)}; //11
	
	
	public RJTPlayerListener(RedstoneJukeboxTrig instance) {
		plugin = instance;
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) { //Made sure it's a right click	
			Block block = event.getClickedBlock();
			if(block.getType() == Material.JUKEBOX) { //Made sure it's a right click on a jukebox
				Jukebox juke = (Jukebox) block.getState();
				if(juke.getPlaying() == Material.AIR){ //Make sure the player is inserting and not taking out
					ItemStack hand = event.getPlayer().getItemInHand();
					if(hand.getType() == Material.GREEN_RECORD || hand.getType() == Material.GOLD_RECORD || hand.getType() == Material.RECORD_3 || hand.getType() == Material.RECORD_4 || hand.getType() == Material.RECORD_5 || hand.getType() == Material.RECORD_6 || hand.getType() == Material.RECORD_7 || hand.getType() == Material.RECORD_8 || hand.getType() == Material.RECORD_9 || hand.getType() == Material.RECORD_10 || hand.getType() == Material.RECORD_11) //Made sure it's putting in a record and not taking it out. Also made sure it's a record that was inserted.
					{
						Material item = event.getMaterial();
						String pos = block.getX() + "," + block.getY() + "," + block.getZ();
						StartTime = System.currentTimeMillis();
						long endTime = findEndTime(StartTime, item);
						posETime = hashIt(pos, endTime);
					}
				}
			}
		}
	}

	public static Map<String, Long> hashIt(String pos, long endTime) {
		posETime.put(pos, endTime);
		return posETime;
	}

	public static long findEndTime(long stTime, Material record) {
		long end = 0;
		long timeAdd = 0;
		if(record == Material.GOLD_RECORD){ //13
			timeAdd = (recordTimes[0]);
		}
		else if(record == Material.GREEN_RECORD){ //cat
			timeAdd = (recordTimes[1]);
		}
		else if(record == Material.RECORD_3){ //blocks
			timeAdd = (recordTimes[2]);
		}
		else if(record == Material.RECORD_4){ //chirp
			timeAdd = (recordTimes[3]);
		}
		else if(record == Material.RECORD_5){ //far
			timeAdd = (recordTimes[4]);
		}
		else if(record == Material.RECORD_6){ //mall
			timeAdd = (recordTimes[5]);
		}
		else if(record == Material.RECORD_7){ //mellohi
			timeAdd = (recordTimes[6]);
		}
		else if(record == Material.RECORD_8){ //stal
			timeAdd = (recordTimes[7]);
		}
		else if(record == Material.RECORD_9){ //strad
			timeAdd = (recordTimes[8]);
		}
		else if(record == Material.RECORD_10){ //ward
			timeAdd = (recordTimes[9]);
		}
		else if(record == Material.RECORD_11){ //11
			timeAdd = (recordTimes[10]);
		}
		
		timeAdd = timeAdd*1000;
		end = stTime + timeAdd;
		return end;
	}
}
