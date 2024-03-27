package com.skegdev.cooldownmanager;

import com.skegdev.startup.ZeusInit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManagerMadman {
    private Map<UUID, Integer> madmanCooldowns = new HashMap<>();

    public CooldownManagerMadman(ZeusInit plugin) {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (UUID uuid : madmanCooldowns.keySet()) {
                    if (madmanCooldowns.get(uuid) == 1) {
                        madmanCooldowns.remove(uuid);
                        continue;
                    }

                    madmanCooldowns.put(uuid, madmanCooldowns.get(uuid) - 1);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }


    public void addPlayerToMapMadman(Player player, Integer time) {
        madmanCooldowns.put(player.getUniqueId(), time);
    }

    public boolean isPlayerInMapMadman(Player player) {
        return madmanCooldowns.containsKey(player.getUniqueId());
    }

    public Integer getTimeRemainingMadman(Player player) {
        if (!isPlayerInMapMadman(player)) {
            return 0;
        } else {
            return madmanCooldowns.get(player.getUniqueId());
        }
    }
}
