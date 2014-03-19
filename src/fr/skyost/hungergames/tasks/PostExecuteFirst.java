package fr.skyost.hungergames.tasks;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import fr.skyost.hungergames.HungerGames;
import fr.skyost.hungergames.HungerGamesProfile;
import fr.skyost.hungergames.HungerGames.Step;
import fr.skyost.hungergames.utils.borders.WorldEditBorder.Type;

public class PostExecuteFirst extends BukkitRunnable {
	
	@Override
	public void run() {
		HungerGames.currentStep = Step.SECOND_COUNTDOWN;
		final String message = HungerGames.messages.Messages_4.replaceAll("/n/", String.valueOf(HungerGames.config.Game_Countdown_Time));
		Player player;
		for(final Entry<Player, HungerGamesProfile> entry : HungerGames.players.entrySet()) {
			player = entry.getKey();
			player.teleport(entry.getValue().getGeneratedLocation());
			player.setGameMode(GameMode.SURVIVAL);
			player.setAllowFlight(false);
			player.setSneaking(HungerGames.config.Game_AutoSneak);
			player.setHealth(player.getMaxHealth());
			player.setFoodLevel(20);
			player.sendMessage(message);
		}
		HungerGames.tasks.set(0, new Countdown(HungerGames.config.Game_Countdown_Time, HungerGames.config.Game_Countdown_ExpBarLevel, new PostExecuteSecond()).runTaskTimer(HungerGames.instance, 0, 20L).getTaskId());
		HungerGames.tasks.set(1, -1);
		if(HungerGames.config.Maps_Borders_Enable && HungerGames.config.Maps_Borders_Type == Type.INVISIBLE) {
			final Location spawn = HungerGames.lobby.getSpawnLocation();
			final int x = spawn.getBlockX();
			final int z = spawn.getBlockZ();
			HungerGames.tasks.set(5, Bukkit.getScheduler().scheduleSyncRepeatingTask(HungerGames.instance, new InvisibleBorderChecker(x + HungerGames.config.Maps_Borders_Radius, x - HungerGames.config.Maps_Borders_Radius, z + HungerGames.config.Maps_Borders_Radius, z - HungerGames.config.Maps_Borders_Radius), 0, 60L));
		}
	}
	
}
