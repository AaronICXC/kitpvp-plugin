package com.skegdev.events;

import com.skegdev.customitems.CustomItems;
import com.skegdev.kits.*;
import com.skegdev.startup.ZeusInit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

public class GUIEvents implements Listener  {
    CustomItems customItems = new CustomItems();
    ZeusInit plugin;
    Kits kits = new Kits();

    public GUIEvents(ZeusInit zeusInit) {
        this.plugin = zeusInit;
    }

    @EventHandler
    public void onClick(InventoryClickEvent clickEvent) {
        if (clickEvent.getClickedInventory() == null) {
            return;
        }

        Player player = (Player) clickEvent.getWhoClicked();

        if (clickEvent.getClickedInventory().getHolder() instanceof KitSelector) {
            clickEvent.setCancelled(true);

            ItemMeta SoupMeta = customItems.Soup.getItemMeta();
            SoupMeta.setDisplayName(ChatColor.DARK_AQUA + "Soup");
            SoupMeta.setLore(Collections.singletonList("Contains \"Mystery Meat\"..?"));
            customItems.Soup.setItemMeta(SoupMeta);

            if (clickEvent.getCurrentItem() == null) {
                return;
            }

            if (plugin.getCooldownManager().isPlayerInMap(player)) {
                player.closeInventory();
                player.sendMessage(ChatColor.RED + "You cannot use this kit yet, " + player.getName() + "." + ChatColor.GOLD + " Time remaining = " + plugin.getCooldownManager().getTimeRemaining(player));
                return;
            }

            // Tank Kit
            if (clickEvent.getSlot() == 0) {

                for (PotionEffect potionEffectType : player.getActivePotionEffects()) {
                    player.removePotionEffect(potionEffectType.getType());
                }

                player.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 1));

                player.setHealth(player.getMaxHealth());

                kits.modifyKits(kits.Tank, 0, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Crystal Crown", Collections.singletonList(""));
                kits.modifyKits(kits.Tank, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 1, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Cuirass", Collections.singletonList(""));
                kits.modifyKits(kits.Tank, 2, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Cuirass Legs Edition", Collections.singletonList(""));
                kits.modifyKits(kits.Tank, 3, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Boots", Collections.singletonList(""));
                kits.modifyKits(kits.Tank, 4, Enchantment.DAMAGE_ALL, 1, Enchantment.DURABILITY, 10, ChatColor.GOLD + "Tank's Sword", Collections.singletonList("Mows through enemies"));

                player.getInventory().setHelmet(kits.Tank[0]);
                player.getInventory().setChestplate(kits.Tank[1]);
                player.getInventory().setLeggings(kits.Tank[2]);
                player.getInventory().setBoots(kits.Tank[3]);

                player.getInventory().setItem(0, kits.Tank[4]);

                ItemMeta TankAbility = kits.Tank[5].getItemMeta();
                TankAbility.setDisplayName(ChatColor.BLUE + "Tank's Shell");
                TankAbility.setLore(Collections.singletonList("When pressing RMB you will gain extreme protection."));
                kits.Tank[5].setItemMeta(TankAbility);

                player.getInventory().setItem(1, kits.Tank[5]);
                player.getInventory().setItem(2, customItems.Soup);
                player.getInventory().setItem(3, customItems.Soup);

                for (int i = 3; i < 36; i++) {
                    player.getInventory().setItem(i, customItems.Soup);
                }

                player.closeInventory();
                player.sendMessage(ChatColor.GOLD + "You have been given the " + ChatColor.GREEN + "Tank Kit.");

                plugin.getCooldownManager().addPlayerToMap(player, 30);
            }

            // Archer Kit
            if (clickEvent.getSlot() == 1 && clickEvent.isLeftClick() && player.getLevel() >= 5) {

                for (PotionEffect potionEffectType : player.getActivePotionEffects()) {
                    player.removePotionEffect(potionEffectType.getType());
                }

                player.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 2));

                player.setHealth(player.getMaxHealth());

                kits.modifyKits(kits.Archer, 0, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Hunter Hat", Collections.singletonList(""));
                kits.modifyKits(kits.Archer, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "T-Shirt", Collections.singletonList(""));
                kits.modifyKits(kits.Archer, 2, Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Pants", Collections.singletonList(""));
                kits.modifyKits(kits.Archer, 3, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Hunter Boots", Collections.singletonList(""));
                kits.modifyKits(kits.Archer, 4, Enchantment.DAMAGE_ALL, 5, Enchantment.FIRE_ASPECT, 3, ChatColor.GOLD + "Huntsman's Hatchet", Collections.singletonList("This thing has chopped down rougly 4 trees"));
                kits.modifyKits(kits.Archer, 5, Enchantment.ARROW_INFINITE, 1, Enchantment.ARROW_DAMAGE, 5, ChatColor.GOLD + "Marksman's Bow", Collections.singletonList("Has hit no bullseyes"));

                player.getInventory().setHelmet(kits.Archer[0]);
                player.getInventory().setChestplate(kits.Archer[1]);
                player.getInventory().setLeggings(kits.Archer[2]);
                player.getInventory().setBoots(kits.Archer[3]);

                ItemMeta ArcherAbility = kits.Archer[6].getItemMeta();
                ArcherAbility.setDisplayName(ChatColor.BLUE + "Anti-Speed");
                ArcherAbility.setLore(Collections.singletonList("When an enemy is smacked with this they are slowed down."));
                kits.Archer[6].setItemMeta(ArcherAbility);

                player.getInventory().setItem(0, kits.Archer[4]);
                player.getInventory().setItem(1, kits.Archer[5]);
                player.getInventory().setItem(2, kits.Archer[6]);

                for (int i = 3; i < 36; i++) {
                    player.getInventory().setItem(i, customItems.Soup);
                }

                player.closeInventory();
                player.sendMessage(ChatColor.GOLD + "You have been given the " + ChatColor.DARK_PURPLE + "Archer Kit");

                plugin.getCooldownManager().addPlayerToMap(player, 30);
            } else if (clickEvent.getSlot() == 1 && clickEvent.isLeftClick() && !(player.getLevel() >= 5)) {
                player.sendMessage(ChatColor.RED + "You have yet to unlock the " + ChatColor.DARK_RED + "Archer Kit");
            }

            if (clickEvent.getSlot() == 1 && clickEvent.isRightClick() && !(player.getLevel() >= 5)) {
                player.sendMessage( ChatColor.BOLD + "You need to reach level 5 to gain access to this kit.");
                player.performCommand("sb");
            } else if (clickEvent.getSlot() == 1 && clickEvent.isRightClick() && player.getLevel() >= 5) {
                player.sendMessage(ChatColor.GOLD + "You already unlocked the " + ChatColor.YELLOW + "Archer Kit!");
            }

            // Chemist Kit
            if (clickEvent.getSlot() == 2 && clickEvent.isLeftClick() && player.getLevel() >= 10) {
                for (PotionEffect potionEffectType : player.getActivePotionEffects()) {
                    player.removePotionEffect(potionEffectType.getType());
                }

                player.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 1));
                player.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(Integer.MAX_VALUE, 2));

                player.setHealth(player.getMaxHealth());

                kits.modifyKits(kits.Chemist, 0, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Tinfoil Hat", Collections.singletonList(""));
                kits.modifyKits(kits.Chemist, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 1, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Rugged Metal", Collections.singletonList(""));
                kits.modifyKits(kits.Chemist, 2, Enchantment.PROTECTION_ENVIRONMENTAL, 1, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Shoes Leg Edition", Collections.singletonList(""));
                kits.modifyKits(kits.Chemist, 3, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Shoes", Collections.singletonList(""));
                kits.modifyKits(kits.Chemist, 4, Enchantment.DAMAGE_ALL, 3, Enchantment.FIRE_ASPECT, 3, ChatColor.GOLD + "Kid's Shovel", Collections.singletonList("Built 104 Sandcastles."));

                player.getInventory().setHelmet(kits.Chemist[0]);
                player.getInventory().setChestplate(kits.Chemist[1]);
                player.getInventory().setLeggings(kits.Chemist[2]);
                player.getInventory().setBoots(kits.Chemist[3]);

                player.getInventory().setItem(0, kits.Chemist[4]);
                player.getInventory().setItem(1, kits.Chemist[5]);
                player.getInventory().setItem(2, kits.Chemist[6]);
                player.getInventory().setItem(3, kits.Chemist[7]);

                for (int i = 4; i < 36; i++) {
                    player.getInventory().setItem(i, customItems.Soup);
                }

                player.closeInventory();
                player.sendMessage(ChatColor.GOLD + "You have been given the " + ChatColor.DARK_BLUE + "Chemist Kit");

                plugin.getCooldownManager().addPlayerToMap(player, 30);

            } else if (clickEvent.getSlot() == 2 && clickEvent.isLeftClick() && !(player.getLevel() >= 10)) {
                player.sendMessage(ChatColor.RED + "You have yet to unlock the " + ChatColor.DARK_RED + "Chemist Kit");
            }

            if (clickEvent.getSlot() == 2 && clickEvent.isRightClick() && !(player.getLevel() >= 10)) {
                player.sendMessage( ChatColor.BOLD + "You need to reach level 10 to gain access to this kit.");
                player.performCommand("sb");
            } else if (clickEvent.getSlot() == 2 && clickEvent.isRightClick() && player.getLevel() >= 10) {
                player.sendMessage(ChatColor.GOLD + "You already unlocked the " + ChatColor.YELLOW + "Chemist Kit!");
            }

            // Madman Kit
            if (clickEvent.getSlot() == 3 && clickEvent.isLeftClick() && player.getLevel() >= 15) {
                for (PotionEffect potionEffectType : player.getActivePotionEffects()) {
                    player.removePotionEffect(potionEffectType.getType());
                }

                player.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 2));
                player.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(Integer.MAX_VALUE, 2));
                player.addPotionEffect(PotionEffectType.REGENERATION.createEffect(Integer.MAX_VALUE, 0));

                player.setHealth(player.getMaxHealth());

                kits.modifyKits(kits.Madman, 0, Enchantment.DAMAGE_ALL, 5, Enchantment.FIRE_ASPECT, 2, ChatColor.DARK_BLUE + "Madman's Wand", Collections.singletonList("Performs greatly."));
                kits.modifyKits(kits.Madman, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 6, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Diamond Tiara", Collections.singletonList(""));

                player.getInventory().setItem(0, kits.Madman[0]);

                player.getInventory().setHelmet(kits.Madman[1]);
                player.getInventory().setChestplate(new ItemStack(Material.AIR));
                player.getInventory().setLeggings(new ItemStack(Material.AIR));
                player.getInventory().setBoots(new ItemStack(Material.AIR));

                ItemMeta MadmanAbility = kits.Madman[2].getItemMeta();
                MadmanAbility.setDisplayName(ChatColor.BLUE + "Amalgamation of Disorientation");
                MadmanAbility.setLore(Collections.singletonList("When an enemy is smacked with this they are confused."));
                kits.Madman[2].setItemMeta(MadmanAbility);

                player.getInventory().setItem(1, kits.Madman[2]);
                player.getInventory().setItem(2, customItems.Soup);
                player.getInventory().setItem(3, customItems.Soup);

                for (int i = 4; i < 36; i++) {
                    player.getInventory().setItem(i, customItems.Soup);
                }

                player.closeInventory();
                player.sendMessage(ChatColor.GOLD + "You have been given the " + ChatColor.DARK_GREEN + "Madman Kit, get help");

                plugin.getCooldownManager().addPlayerToMap(player, 30);
            } else if (clickEvent.getSlot() == 3 && clickEvent.isLeftClick() && !(player.getLevel() >= 15)) {
                player.sendMessage(ChatColor.RED + "You have yet to unlock the " + ChatColor.DARK_RED + "Madman Kit");
            }

            if (clickEvent.getSlot() == 3 && clickEvent.isRightClick() && !(player.getLevel() >= 15)) {
                player.sendMessage( ChatColor.BOLD + "You need to reach level 15 to gain access to this kit.");
                player.performCommand("sb");
            } else if (clickEvent.getSlot() == 3 && clickEvent.isRightClick() && player.getLevel() >= 15) {
                player.sendMessage(ChatColor.GOLD + "You already unlocked the " + ChatColor.YELLOW + "Madman Kit!");
            }

            // Berserker Kit
            if (clickEvent.getSlot() == 4 && clickEvent.isLeftClick() && player.getLevel() >= 20) {
                for (PotionEffect potionEffectType : player.getActivePotionEffects()) {
                    player.removePotionEffect(potionEffectType.getType());
                }

                player.addPotionEffect(PotionEffectType.HEALTH_BOOST.createEffect(Integer.MAX_VALUE, 5));

                player.setHealth(player.getMaxHealth());

                kits.modifyKits(kits.Berserker, 0, Enchantment.DAMAGE_ALL, 3, Enchantment.DURABILITY, 10, ChatColor.GRAY + "Berserker's Axe", Collections.singletonList("Failed to kill a pig."));
                kits.modifyKits(kits.Berserker, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Ragged Crown", Collections.singletonList(""));
                kits.modifyKits(kits.Berserker, 2, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Fake Gucci", Collections.singletonList(""));
                kits.modifyKits(kits.Berserker, 3, Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Cuirass Leg Edition", Collections.singletonList(""));
                kits.modifyKits(kits.Berserker, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Boots", Collections.singletonList(""));

                player.getInventory().setItem(0, kits.Berserker[0]);
                player.getInventory().setHelmet(kits.Berserker[1]);
                player.getInventory().setChestplate(kits.Berserker[2]);
                player.getInventory().setLeggings(kits.Berserker[3]);
                player.getInventory().setBoots(kits.Berserker[4]);

                ItemMeta BerserkerAbility = kits.Berserker[5].getItemMeta();
                BerserkerAbility.setDisplayName(ChatColor.BLUE + "War Cry");
                BerserkerAbility.setLore(Collections.singletonList("When pressing RMB your strength & speed accelerate & nearby enemies are vulnerable."));
                kits.Berserker[5].setItemMeta(BerserkerAbility);

                player.getInventory().setItem(1, kits.Berserker[5]);
                player.getInventory().setItem(2, customItems.Soup);
                player.getInventory().setItem(3, customItems.Soup);

                for (int i = 4; i < 36; i++) {
                    player.getInventory().setItem(i, customItems.Soup);
                }

                player.closeInventory();
                player.sendMessage(ChatColor.GOLD + "You have been given the " + ChatColor.BOLD + "Berserker Kit.");
            } else if (clickEvent.getSlot() == 4 && clickEvent.isLeftClick() && !(player.getLevel() >= 20)) {
                player.sendMessage(ChatColor.RED + "You have yet to unlock the " + ChatColor.DARK_RED + "Berserker Kit");
            }

            if (clickEvent.getSlot() == 4 && clickEvent.isRightClick() && !(player.getLevel() >= 20)) {
                player.sendMessage( ChatColor.BOLD + "You need to reach level 20 to gain access to this kit.");
            } else if (clickEvent.getSlot() == 4 && clickEvent.isRightClick() && player.getLevel() >= 20) {
                player.sendMessage(ChatColor.GOLD + "You already unlocked the " + ChatColor.YELLOW + "Berserker Kit!");
            }

            // Vampire Kit
            if (clickEvent.getSlot() == 5 && clickEvent.isLeftClick() && player.getLevel() >= 25) {
                for (PotionEffect potionEffectType : player.getActivePotionEffects()) {
                    player.removePotionEffect(potionEffectType.getType());
                }

                player.addPotionEffect(PotionEffectType.REGENERATION.createEffect(Integer.MAX_VALUE, 0));
                player.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 1));

                player.setHealth(player.getMaxHealth());

                kits.modifyKits(kits.Vampire, 0, Enchantment.DAMAGE_ALL, 3, Enchantment.DURABILITY, 10, ChatColor.RED + "Vampire's Dagger", Collections.singletonList("Killed many pigs."));
                kits.modifyKits(kits.Vampire, 1, Enchantment.DAMAGE_UNDEAD, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Vampire's Tooth", Collections.singletonList("Sucks blood like a straw."));
                kits.modifyKits(kits.Vampire, 2, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Vampire's Cowl", Collections.singletonList(""));
                kits.modifyKits(kits.Vampire, 3, Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Vampire's Cloak", Collections.singletonList(""));
                kits.modifyKits(kits.Vampire, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Vampire's Trousers", Collections.singletonList(""));
                kits.modifyKits(kits.Vampire, 5, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.DARK_PURPLE + "Vampire's Sandals", Collections.singletonList(""));

                player.getInventory().setItem(0, kits.Vampire[0]);
                player.getInventory().setItem(1, kits.Vampire[1]);
                player.getInventory().setHelmet(kits.Vampire[2]);
                player.getInventory().setChestplate(kits.Vampire[3]);
                player.getInventory().setLeggings(kits.Vampire[4]);
                player.getInventory().setBoots(kits.Vampire[5]);

                for (int i = 2; i < 36; i++) {
                    player.getInventory().setItem(i, customItems.Soup);
                }

                player.closeInventory();
                player.sendMessage(ChatColor.GOLD + "You have been given the " + ChatColor.BOLD + "Vampire Kit.");
            } else if (clickEvent.getSlot() == 5 && clickEvent.isLeftClick() && !(player.getLevel() >= 25)) {
                player.sendMessage(ChatColor.RED + "You have yet to unlock the " + ChatColor.DARK_RED + "Vampire Kit");
            }

            if (clickEvent.getSlot() == 5 && clickEvent.isRightClick() && !(player.getLevel() >= 25)) {
                player.sendMessage( ChatColor.BOLD + "You need to reach level 25 to gain access to this kit.");
            } else if (clickEvent.getSlot() == 5 && clickEvent.isRightClick() && player.getLevel() >= 25) {
                player.sendMessage(ChatColor.GOLD + "You already unlocked the " + ChatColor.YELLOW + "Vampire Kit!");
            }

            // Pyro Kit
            if (clickEvent.getSlot() == 6 && clickEvent.isLeftClick() && player.getLevel() >= 30) {
                for (PotionEffect potionEffectType : player.getActivePotionEffects()) {
                    player.removePotionEffect(potionEffectType.getType());
                }

                player.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(Integer.MAX_VALUE, 9));
                player.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 0));

                player.setHealth(player.getMaxHealth());

                kits.modifyKits(kits.Pyro, 0, Enchantment.DAMAGE_ALL, 3, Enchantment.DURABILITY, 10, ChatColor.RED + "Pyro's Hatchet", Collections.singletonList("Used for Lumberjack-ing"));
                kits.modifyKits(kits.Pyro, 1, Enchantment.DAMAGE_UNDEAD, 2, Enchantment.DURABILITY, 10, ChatColor.LIGHT_PURPLE + "Pyro's Lighter", Collections.singletonList("Attempted to reignite 102 forest fires"));
                kits.modifyKits(kits.Pyro, 2, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.LIGHT_PURPLE + "Pyro's Cap", Collections.singletonList(""));
                kits.modifyKits(kits.Pyro, 3, Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 10, ChatColor.LIGHT_PURPLE + "Pyro's Chains", Collections.singletonList(""));
                kits.modifyKits(kits.Pyro, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.LIGHT_PURPLE + "Pyro's Leg Chains", Collections.singletonList(""));
                kits.modifyKits(kits.Pyro, 5, Enchantment.PROTECTION_ENVIRONMENTAL, 2, Enchantment.DURABILITY, 10, ChatColor.LIGHT_PURPLE + "Pyro's Heels", Collections.singletonList(""));

                player.getInventory().setItem(0, kits.Pyro[0]);
                player.getInventory().setItem(1, kits.Pyro[1]);
                player.getInventory().setHelmet(kits.Pyro[2]);
                player.getInventory().setChestplate(kits.Pyro[3]);
                player.getInventory().setLeggings(kits.Pyro[4]);
                player.getInventory().setBoots(kits.Pyro[5]);

                for (int i = 2; i < 36; i++) {
                    player.getInventory().setItem(i, customItems.Soup);
                }

                player.closeInventory();
                player.sendMessage(ChatColor.GOLD + "You have been given the " + ChatColor.BOLD + "Pyro Kit.");
            } else if (clickEvent.getSlot() == 5 && clickEvent.isLeftClick() && !(player.getLevel() >= 30)) {
                player.sendMessage(ChatColor.RED + "You have yet to unlock the " + ChatColor.DARK_RED + "Pyro Kit");
            }

            if (clickEvent.getSlot() == 6 && clickEvent.isRightClick() && !(player.getLevel() >= 30)) {
                player.sendMessage( ChatColor.BOLD + "You need to reach level 25 to gain access to this kit.");
            } else if (clickEvent.getSlot() == 6 && clickEvent.isRightClick() && player.getLevel() >= 30) {
                player.sendMessage(ChatColor.GOLD + "You already unlocked the " + ChatColor.YELLOW + "Pyro Kit!");
            }

        }


    }
}
