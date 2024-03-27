package com.skegdev.kits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class OpenSelector implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that command...");
        }

        Player player = (Player) sender;
        KitSelector kitSelector = new KitSelector();

        if (command.getName().equalsIgnoreCase("kit")) {
            player.openInventory(kitSelector.getInventory());
        }

        return true;
    }
}
