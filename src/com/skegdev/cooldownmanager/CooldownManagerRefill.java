package com.skegdev.cooldownmanager;

import com.skegdev.startup.ZeusInit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManagerRefill {
    private Map<UUID, Integer> refillCooldowns = new HashMap<>();

    public CooldownManagerRefill(ZeusInit plugin) {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (UUID uuid : refillCooldowns.keySet()) {
                    if (refillCooldowns.get(uuid) == 1) {
                        refillCooldowns.remove(uuid);
                        continue;
                    }

                    refillCooldowns.put(uuid, refillCooldowns.get(uuid) - 1);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }


    public void addPlayerToMapRefill(Player player, Integer time) {
        refillCooldowns.put(player.getUniqueId(), time);
    }

    public boolean isPlayerInMapRefill(Player player) {
        return refillCooldowns.containsKey(player.getUniqueId());
    }

    public Integer getTimeRemainingRefill(Player player) {
        if (!isPlayerInMapRefill(player)) {
            return 0;
        } else {
            return refillCooldowns.get(player.getUniqueId());
        }
    }
}
