package com.skegdev.cooldownmanager;

import com.skegdev.startup.ZeusInit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManagerBerserker {
    private Map<UUID, Integer> berserkerCooldowns = new HashMap<>();

    public CooldownManagerBerserker(ZeusInit plugin) {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (UUID uuid : berserkerCooldowns.keySet()) {
                    if (berserkerCooldowns.get(uuid) == 1) {
                        berserkerCooldowns.remove(uuid);
                        continue;
                    }

                    berserkerCooldowns.put(uuid, berserkerCooldowns.get(uuid) - 1);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }


    public void addPlayerToMapBerserker(Player player, Integer time) {
        berserkerCooldowns.put(player.getUniqueId(), time);
    }

    public boolean isPlayerInMapBerserker(Player player) {
        return berserkerCooldowns.containsKey(player.getUniqueId());
    }

    public Integer getTimeRemainingBerserker(Player player) {
        if (!isPlayerInMapBerserker(player)) {
            return 0;
        } else {
            return berserkerCooldowns.get(player.getUniqueId());
        }
    }
}
