package com.skegdev.cooldownmanager;

import com.skegdev.startup.ZeusInit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManagerVampire {
    private Map<UUID, Integer> vampireCooldowns = new HashMap<>();

    public CooldownManagerVampire(ZeusInit plugin) {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (UUID uuid : vampireCooldowns.keySet()) {
                    if (vampireCooldowns.get(uuid) == 1) {
                        vampireCooldowns.remove(uuid);
                        continue;
                    }

                    vampireCooldowns.put(uuid, vampireCooldowns.get(uuid) - 1);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }


    public void addPlayerToMapVampire(Player player, Integer time) {
        vampireCooldowns.put(player.getUniqueId(), time);
    }

    public boolean isPlayerInMapVampire(Player player) {
        return vampireCooldowns.containsKey(player.getUniqueId());
    }

    public Integer getTimeRemainingVampire(Player player) {
        if (!isPlayerInMapVampire(player)) {
            return 0;
        } else {
            return vampireCooldowns.get(player.getUniqueId());
        }
    }
}
