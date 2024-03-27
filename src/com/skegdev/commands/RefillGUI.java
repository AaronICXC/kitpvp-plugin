package com.skegdev.commands;

import com.skegdev.customitems.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class RefillGUI implements InventoryHolder {
    CustomItems customItems = new CustomItems();
    private Inventory inv;

    public RefillGUI() {
        inv = Bukkit.createInventory(this, 27, ChatColor.DARK_PURPLE + "Refill");
        init();
    }

    private void init() {
        ItemMeta SoupMeta = customItems.Soup.getItemMeta();
        SoupMeta.setDisplayName(ChatColor.DARK_AQUA + "Soup");
        SoupMeta.setLore(Collections.singletonList("Contains \"Mystery Meat\"..?"));
        customItems.Soup.setItemMeta(SoupMeta);

        for (int i = 0; i < 27; i++) {
            inv.setItem(i, customItems.Soup);
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
