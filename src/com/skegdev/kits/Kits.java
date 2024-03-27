package com.skegdev.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Kits {

    public ItemStack[] Tank = {
             new ItemStack(Material.DIAMOND_HELMET),
             new ItemStack(Material.IRON_CHESTPLATE),
             new ItemStack(Material.IRON_LEGGINGS),
             new ItemStack(Material.DIAMOND_BOOTS),
             new ItemStack(Material.DIAMOND_SWORD),
             new ItemStack(Material.BEACON)
    };

    public ItemStack[] Archer = {
             new ItemStack(Material.LEATHER_HELMET),
             new ItemStack(Material.LEATHER_CHESTPLATE),
             new ItemStack(Material.LEATHER_LEGGINGS),
             new ItemStack(Material.LEATHER_BOOTS),
             new ItemStack(Material.DIAMOND_AXE),
             new ItemStack(Material.BOW),
             new ItemStack(Material.ARROW)
    };

    public ItemStack[] Chemist = {
            new ItemStack(Material.CHAINMAIL_HELMET),
            new ItemStack(Material.IRON_CHESTPLATE),
            new ItemStack(Material.GOLD_LEGGINGS),
            new ItemStack(Material.DIAMOND_BOOTS),
            new ItemStack(Material.DIAMOND_SPADE),
            new ItemStack(Material.POTION, 3, (short) 16420),
            new ItemStack(Material.POTION, 5, (short) 16428),
            new ItemStack(Material.POTION, 10, (short) 16421)
    };

    public ItemStack[] Madman = {
            new ItemStack(Material.STICK),
            new ItemStack(Material.DIAMOND_HELMET),
            new ItemStack(Material.FEATHER)
    };

    public ItemStack[] Berserker = {
            new ItemStack(Material.STONE_AXE),
            new ItemStack(Material.IRON_HELMET),
            new ItemStack(Material.LEATHER_CHESTPLATE),
            new ItemStack(Material.DIAMOND_LEGGINGS),
            new ItemStack(Material.DIAMOND_BOOTS),
            new ItemStack(Material.REDSTONE)
    };

    public ItemStack[] Vampire = {
            new ItemStack(Material.IRON_SWORD),
            new ItemStack(Material.GHAST_TEAR),
            new ItemStack(Material.CHAINMAIL_HELMET),
            new ItemStack(Material.LEATHER_CHESTPLATE),
            new ItemStack(Material.LEATHER_LEGGINGS),
            new ItemStack(Material.IRON_BOOTS)
    };

    public ItemStack[] Pyro = {
            new ItemStack(Material.IRON_AXE),
            new ItemStack(Material.FLINT_AND_STEEL),
            new ItemStack(Material.GOLD_HELMET),
            new ItemStack(Material.CHAINMAIL_CHESTPLATE),
            new ItemStack(Material.CHAINMAIL_LEGGINGS),
            new ItemStack(Material.GOLD_BOOTS)
    };

    public void modifyKits(ItemStack[] kit, int kitNumber, Enchantment ench, int enchLevel, Enchantment ench2, int enchLevel2, String name, List lore) {
        kit[kitNumber].addUnsafeEnchantment(ench, enchLevel);
        kit[kitNumber].addUnsafeEnchantment(ench2, enchLevel2);

        ItemMeta kitMeta = kit[kitNumber].getItemMeta();
        kitMeta.setDisplayName(name);
        kitMeta.setLore(lore);
        kit[kitNumber].setItemMeta(kitMeta);
    }
}
