package com.skegdev.commands;

import com.skegdev.startup.ZeusInit;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class ReclaimCommand implements CommandExecutor {

    ZeusInit plugin;

    public ReclaimCommand(ZeusInit zeusInit) {
        this.plugin = zeusInit;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
        }

        Player player = (Player) sender;

        ItemStack dailyKey = new ItemStack(Material.TRIPWIRE_HOOK, 5);
        dailyKey.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 10);

        ItemMeta dailyKeyMeta = dailyKey.getItemMeta();
        dailyKeyMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Daily" + ChatColor.WHITE + " key");
        dailyKeyMeta.setLore(Collections.singletonList("Used for benefits (DAILY)"));
        dailyKey.setItemMeta(dailyKeyMeta);

        ItemStack weeklyKey = new ItemStack(Material.TRIPWIRE_HOOK, 3);
        weeklyKey.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 10);

        ItemMeta weeklyKeyMeta = weeklyKey.getItemMeta();
        weeklyKeyMeta.setDisplayName(ChatColor.DARK_AQUA + "Weekly" + ChatColor.WHITE + " key");
        weeklyKeyMeta.setLore(Collections.singletonList("Used for benefits (WEEKLY)"));
        weeklyKey.setItemMeta(weeklyKeyMeta);

        ItemStack monthlyKey = new ItemStack(Material.TRIPWIRE_HOOK);
        monthlyKey.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 10);

        ItemMeta monthlyKeyMeta = monthlyKey.getItemMeta();
        monthlyKeyMeta.setDisplayName(ChatColor.RED + "Monthly" + ChatColor.WHITE + " key");
        monthlyKeyMeta.setLore(Collections.singletonList("Used for benefits (MONTHLY)"));
        monthlyKey.setItemMeta(monthlyKeyMeta);

        if (command.getName().equalsIgnoreCase("reclaim")) {

            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("daily") && !(plugin.getReclaimCooldownsDaily().isPlayerInMapReclaim(player))) {
                    player.getInventory().setItem(34, dailyKey);
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You have reclaimed your " + ChatColor.BOLD + "Daily" + ChatColor.LIGHT_PURPLE + " keys");
                    plugin.getReclaimCooldownsDaily().addPlayerToMapReclaim(player, 86400);
                    }
                } else if (args[0].equalsIgnoreCase("daily") && plugin.getReclaimCooldownsDaily().isPlayerInMapReclaim(player)) {
                    player.sendMessage(ChatColor.RED + "You already reclaimed your " + ChatColor.BOLD + "Daily" + ChatColor.RED + " keys for the day.");
                } else if (args[0].equalsIgnoreCase("weekly") && !(plugin.getReclaimCooldownsWeekly().isPlayerInMapReclaim(player))) {
                    player.getInventory().setItem(35, weeklyKey);
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You have reclaimed your " + ChatColor.BOLD + "Weekly" + ChatColor.LIGHT_PURPLE + " keys");
                    plugin.getReclaimCooldownsWeekly().addPlayerToMapReclaim(player, 604800);
                } else if (args[0].equalsIgnoreCase("weekly") && plugin.getReclaimCooldownsWeekly().isPlayerInMapReclaim(player)) {
                    player.sendMessage(ChatColor.RED + "You already reclaimed your " + ChatColor.BOLD + "Weekly" + ChatColor.RED + " keys for the week.");
                } else if (args[0].equalsIgnoreCase("monthly") && !(plugin.getReclaimCooldownsMonthly().isPlayerInMapReclaim(player))) {
                    player.getInventory().setItem(36, monthlyKey);
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You have reclaimed your " + ChatColor.BOLD + "Monthly" + ChatColor.LIGHT_PURPLE + " key");
                    plugin.getReclaimCooldownsMonthly().addPlayerToMapReclaim(player, 2628000L);
                } else if (args[0].equalsIgnoreCase("monthly") && plugin.getReclaimCooldownsMonthly().isPlayerInMapReclaim(player)) {
                    player.sendMessage(ChatColor.RED + "You already reclaimed your " + ChatColor.BOLD + "Monthly" + ChatColor.RED + " key for the month.");
                } else {
                    player.sendMessage(ChatColor.GOLD + "/reclaim <Daily, Weekly, Monthly>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "/reclaim <Daily, Weekly, Monthly>");
            }
        return true;
    }
}
