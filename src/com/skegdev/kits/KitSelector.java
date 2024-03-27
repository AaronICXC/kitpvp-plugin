package com.skegdev.kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class KitSelector implements InventoryHolder {
    private Inventory inv;

    public KitSelector() {
        inv = Bukkit.createInventory(this, 18, ChatColor.RED + "Kits");
        init();
    }

    private void init() {
        ItemStack TankKit = createItem(ChatColor.GREEN + "Tank", Material.DIAMOND_SWORD, Collections.singletonList("Heavy Lifter"));
        ItemStack ArcherKit = createItem(ChatColor.GOLD + "Archer", Material.BOW, Collections.singletonList("Aim"));
        ItemStack ChemistKit = createItem(ChatColor.DARK_PURPLE + "Chemist", Material.POTION, Collections.singletonList("Illegal Substances"));
        ItemStack MadmanKit = createItem(ChatColor.GRAY + "Madman", Material.STICK, Collections.singletonList("What's wrong with you."));
        ItemStack BersekerKit = createItem(ChatColor.BOLD + "Berserker", Material.IRON_HELMET, Collections.singletonList("GO CRAZY, GO STUPID"));
        ItemStack VampireKit = createItem( ChatColor.DARK_RED + "" + ChatColor.ITALIC + "Vampire", Material.GHAST_TEAR, Collections.singletonList("Blood is cool."));
        ItemStack PyroKit = createItem(ChatColor.YELLOW + "Pyromaniac", Material.FIREBALL, Collections.singletonList("Fire"));

        inv.addItem(TankKit);
        inv.addItem(ArcherKit);
        inv.addItem(ChemistKit);
        inv.addItem(MadmanKit);
        inv.addItem(BersekerKit);
        inv.addItem(VampireKit);
        inv.addItem(PyroKit);
    }

    private ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    };


    @Override
    public Inventory getInventory() {
        return inv;
    }
}
