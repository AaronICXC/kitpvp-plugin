package com.skegdev.cooldownmanager;

import com.skegdev.startup.ZeusInit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManagerTank {
    private Map<UUID, Integer> tankCooldowns = new HashMap<>();

    public CooldownManagerTank(ZeusInit plugin) {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (UUID uuid : tankCooldowns.keySet()) {
                    if (tankCooldowns.get(uuid) == 1) {
                        tankCooldowns.remove(uuid);
                        continue;
                    }

                    tankCooldowns.put(uuid, tankCooldowns.get(uuid) - 1);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }


    public void addPlayerToMapTank(Player player, Integer time) {
        tankCooldowns.put(player.getUniqueId(), time);
    }

    public boolean isPlayerInMapTank(Player player) {
        return tankCooldowns.containsKey(player.getUniqueId());
    }

    public Integer getTimeRemainingTank(Player player) {
        if (!isPlayerInMapTank(player)) {
            return 0;
        } else {
            return tankCooldowns.get(player.getUniqueId());
        }
    }
}
