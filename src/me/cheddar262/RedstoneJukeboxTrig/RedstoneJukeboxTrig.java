package me.cheddar262.RedstoneJukeboxTrig;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Jukebox;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class RedstoneJukeboxTrig extends JavaPlugin{
	private Logger log = Logger.getLogger("Minecraft");
	private final RJTBlockListener blockListener = new RJTBlockListener(this);
	private final RJTPlayerListener playerListener = new RJTPlayerListener(this);
    private Map<String, Long> positionEndtimeMap = Collections.synchronizedMap(new HashMap<String, Long> ());
	private static long[] recordTimes = { //(Minutes * 60) + seconds
			((2*60)+58), //13
			((3*60)+5), //cat
			((5*60)+45), //blocks
			((3*60)+5), //chirp
			((2*60)+54), //far
			((3*60)+17), //mall
			((1*60)+36), //mellohi
			((2*60)+30), //stal
			((3*60)+8), //strad
			((4*60)+11), //ward
			((1*60)+11), //11
			((3*60)+51)}; //wait

	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		
		log.info("RedstoneJukeboxTrig has been enabled!");
		pm.registerEvents(blockListener, this);
		pm.registerEvents(playerListener, this);
	}
	public void onDisable() {
		log.info("RedstoneJukeboxTrig has been disabled.");
	}	


	public long findEndTime(long startTime, Material record) {
		long timeOffset = 0;
		if(record == Material.GOLD_RECORD){ //13
			timeOffset = (recordTimes[0]);
		}
		else if(record == Material.GREEN_RECORD){ //cat
			timeOffset = (recordTimes[1]);
		}
		else if(record == Material.RECORD_3){ //blocks
			timeOffset = (recordTimes[2]);
		}
		else if(record == Material.RECORD_4){ //chirp
			timeOffset = (recordTimes[3]);
		}
		else if(record == Material.RECORD_5){ //far
			timeOffset = (recordTimes[4]);
		}
		else if(record == Material.RECORD_6){ //mall
			timeOffset = (recordTimes[5]);
		}
		else if(record == Material.RECORD_7){ //mellohi
			timeOffset = (recordTimes[6]);
		}
		else if(record == Material.RECORD_8){ //stal
			timeOffset = (recordTimes[7]);
		}
		else if(record == Material.RECORD_9){ //strad
			timeOffset = (recordTimes[8]);
		}
		else if(record == Material.RECORD_10){ //ward
			timeOffset = (recordTimes[9]);
		}
		else if(record == Material.RECORD_11){ //11
			timeOffset = (recordTimes[10]);
		}
		else if(record == Material.RECORD_12){ //wait
			timeOffset = (recordTimes[11]);
		}

		return startTime + timeOffset*1000;
	}
	
	public Material getPlaying(Jukebox jb) {
	    try {
	        jb.getPlaying();
	        return jb.getPlaying();
	    } catch (Exception e) {
	        return Material.AIR;
	    }
	}


    public Map<String, Long> getPositionEndtimeMap() {
        return positionEndtimeMap;
    }
}