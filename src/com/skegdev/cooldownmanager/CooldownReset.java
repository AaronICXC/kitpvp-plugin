package com.skegdev.cooldownmanager;

import com.skegdev.startup.ZeusInit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CooldownReset implements CommandExecutor {

    ZeusInit plugin;

    public CooldownReset(ZeusInit zeusInit) {
        this.plugin = zeusInit;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player player = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("resetcooldown") && plugin.getCooldownManager().isPlayerInMap(player)) {
            plugin.getCooldownManager().removePlayerFromMap(player);
            player.sendMessage(ChatColor.GREEN + "Your cooldown has been removed!");
        } else if (command.getName().equalsIgnoreCase("resetcooldown") && !(plugin.getCooldownManager().isPlayerInMap(player)) || !(plugin.getRefillCooldowns().isPlayerInMapRefill(player))) {
            player.sendMessage(ChatColor.RED + "You are not in a cooldown.");
        } else if (command.getName().equalsIgnoreCase("resetcooldown") && (plugin.getRefillCooldowns().isPlayerInMapRefill(player))) {
            player.sendMessage(ChatColor.RED + "You cannot reset the cooldown for refills.");
        }

        return true;
    }


}
