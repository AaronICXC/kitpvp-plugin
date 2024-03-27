package com.skegdev.cooldownmanager;

import com.skegdev.startup.ZeusInit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private Map<UUID, Integer> cooldowns = new HashMap<>();

    public CooldownManager(ZeusInit plugin) {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (UUID uuid : cooldowns.keySet()) {
                    if (cooldowns.get(uuid) == 1) {
                        cooldowns.remove(uuid);
                        continue;
                    }

                    cooldowns.put(uuid, cooldowns.get(uuid) - 1);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    public void addPlayerToMap(Player player, Integer time) {
        cooldowns.put(player.getUniqueId(), time);
    }

    public boolean isPlayerInMap(Player player) {
        return cooldowns.containsKey(player.getUniqueId());
    }

    public Integer getTimeRemaining(Player player) {
        if (!isPlayerInMap(player)) {
            return 0;
        } else {
            return cooldowns.get(player.getUniqueId());
        }
    }

    public Integer removePlayerFromMap(Player player) {
        return cooldowns.remove(player.getUniqueId());
    }


}
