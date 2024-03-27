package com.skegdev.commands;

import com.skegdev.startup.ZeusInit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RefillCommand implements CommandExecutor {
    RefillGUI refillGUI = new RefillGUI();
    ZeusInit plugin;

    public RefillCommand(ZeusInit zeusInit) {
        this.plugin = zeusInit;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (plugin.getRefillCooldowns().isPlayerInMapRefill(player)) {
            player.closeInventory();
            player.sendMessage(ChatColor.RED + "You cannot use this yet, " + player.getName() + "." + ChatColor.GOLD + " Time remaining = " + plugin.getRefillCooldowns().getTimeRemainingRefill(player));
            return true;
        }

        if (command.getName().equalsIgnoreCase("refill") && !(plugin.getRefillCooldowns().isPlayerInMapRefill(player))) {
            player.openInventory(refillGUI.getInventory());

            plugin.getRefillCooldowns().addPlayerToMapRefill(player, 90);
        } else if (command.getName().equalsIgnoreCase("refill") && plugin.getRefillCooldowns().isPlayerInMapRefill(player)) {
            player.sendMessage(ChatColor.RED + "You cannot use this yet, " + player.getName() + "." + ChatColor.GOLD + " Time remaining = " + plugin.getRefillCooldowns().getTimeRemainingRefill(player));
        }

        return true;
    }
}
