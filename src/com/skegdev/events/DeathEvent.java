package com.skegdev.events;

import com.skegdev.scoreboard.ZeusScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getServer;


public class DeathEvent implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent deathEvent) {
        Player player = deathEvent.getEntity();
        if (player.getKiller() == null)
            getServer().getConsoleSender().sendMessage("No killer");

        if (player.getKiller() == player) {
            player.sendMessage(ChatColor.ITALIC + "You died to yourself? lol");
        }

        int amount = (int) player.getKiller().getHealth() * 2;

        player.getInventory().clear();
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));

        if (player.getKiller().getItemInHand() == null) {
            deathEvent.setDeathMessage(ChatColor.GOLD + player.getKiller().getName() + ChatColor.BLUE + " Killed " + ChatColor.DARK_PURPLE + player.getName() + " using " + "[" + ChatColor.GOLD + "Hand" + ChatColor.DARK_PURPLE + "]");
        }
        deathEvent.setDeathMessage(ChatColor.GOLD + player.getKiller().getName() + ChatColor.BLUE + " Killed " + ChatColor.DARK_PURPLE + player.getName() + " using " + "[" + ChatColor.GOLD + player.getKiller().getItemInHand().getItemMeta().getDisplayName() + ChatColor.DARK_PURPLE + "]");

        player.sendMessage(ChatColor.DARK_RED + "You Died to " + ChatColor.DARK_PURPLE + player.getKiller().getName() + "!");

        player.getKiller().giveExp(amount);
        player.getKiller().sendMessage(ChatColor.GOLD + "You earned " + amount + " experience!");
    }
}
