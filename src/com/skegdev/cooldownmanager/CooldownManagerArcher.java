package com.skegdev.cooldownmanager;

import com.skegdev.startup.ZeusInit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManagerArcher {
    private Map<UUID, Integer> archerCooldowns = new HashMap<>();

    public CooldownManagerArcher(ZeusInit plugin) {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (UUID uuid : archerCooldowns.keySet()) {
                    if (archerCooldowns.get(uuid) == 1) {
                        archerCooldowns.remove(uuid);
                        continue;
                    }

                    archerCooldowns.put(uuid, archerCooldowns.get(uuid) - 1);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }


    public void addPlayerToMapArcher(Player player, Integer time) {
        archerCooldowns.put(player.getUniqueId(), time);
    }

    public boolean isPlayerInMapArcher(Player player) {
        return archerCooldowns.containsKey(player.getUniqueId());
    }

    public Integer getTimeRemainingArcher(Player player) {
        if (!isPlayerInMapArcher(player)) {
            return 0;
        } else {
            return archerCooldowns.get(player.getUniqueId());
        }
    }
}
