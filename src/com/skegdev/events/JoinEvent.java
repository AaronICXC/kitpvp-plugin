package com.skegdev.events;

import com.skegdev.scoreboard.ZeusScoreboard;
import com.skegdev.startup.ZeusInit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinEvent implements Listener {
    ZeusInit plugin;

    public JoinEvent(ZeusInit zeusInit) {
        this.plugin = zeusInit;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        joinEvent.setJoinMessage(ChatColor.GOLD + player.getName() + " has joined Waffle" + ChatColor.BLUE + "PvP!");
        player.getInventory().clear();

        plugin.getZeusScoreboard().createScoreboard(player);

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent quitEvent) {
        Player player = quitEvent.getPlayer();
        quitEvent.setQuitMessage(ChatColor.GOLD + player.getName() + " has left Waffle" + ChatColor.BLUE + "PvP " + ChatColor.BOLD + "to get milk.");
    }
}
