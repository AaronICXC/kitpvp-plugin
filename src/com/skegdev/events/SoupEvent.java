package com.skegdev.events;

import com.skegdev.customitems.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class SoupEvent implements Listener {
    CustomItems customItems = new CustomItems();

    @EventHandler
    public void onSoup(PlayerInteractEvent interactEvent) {
        Player player = interactEvent.getPlayer();

        ItemMeta SoupRemnantMeta = customItems.SoupRemnant.getItemMeta();
        SoupRemnantMeta.setDisplayName(ChatColor.DARK_AQUA + "Soup Remnant");
        SoupRemnantMeta.setLore(Collections.singletonList("Still has some drops left..."));
        customItems.SoupRemnant.setItemMeta(SoupRemnantMeta);

        if (player.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
            interactEvent.setCancelled(true);
            double health = player.getHealth();
            double maxHealth = player.getMaxHealth();

            if (health < maxHealth && health >= maxHealth - 7) {
                player.setHealth(maxHealth);
                interactEvent.getPlayer().getInventory().setItemInHand(new ItemStack(Material.AIR));
                interactEvent.getPlayer().getInventory().setItemInHand(customItems.SoupRemnant);
            } else if (health < maxHealth && health < maxHealth - 7) {
                player.setHealth(health + 7);
                interactEvent.getPlayer().getInventory().setItemInHand(new ItemStack(Material.AIR));
                interactEvent.getPlayer().getInventory().setItemInHand(customItems.SoupRemnant);
            }
        }
    }
}
