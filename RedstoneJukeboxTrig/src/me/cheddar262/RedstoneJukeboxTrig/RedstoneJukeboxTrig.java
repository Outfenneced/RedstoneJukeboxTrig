package me.cheddar262.RedstoneJukeboxTrig;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class RedstoneJukeboxTrig extends JavaPlugin{
	Logger log = Logger.getLogger("Minecraft");
	private final RJTBlockListener blockListener = new RJTBlockListener(this);
	private final RJTPlayerListener playerListener = new RJTPlayerListener(this);
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		
		log.info("RedstoneJukeboxTrig has been enabled!");
		pm.registerEvents(blockListener, this);
		pm.registerEvents(playerListener, this);
	}
	public void onDisable() {
		log.info("RedstoneJukeboxTrig has been disabled.");
		
	}
	
}
