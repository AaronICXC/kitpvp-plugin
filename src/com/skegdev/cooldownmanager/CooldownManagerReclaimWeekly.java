package com.skegdev.cooldownmanager;

import com.skegdev.startup.ZeusInit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManagerReclaimWeekly {
    private Map<UUID, Integer> reclaimCooldowns = new HashMap<>();

    public CooldownManagerReclaimWeekly(ZeusInit plugin) {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (UUID uuid : reclaimCooldowns.keySet()) {
                    if (reclaimCooldowns.get(uuid) == 1) {
                        reclaimCooldowns.remove(uuid);
                        continue;
                    }

                    reclaimCooldowns.put(uuid, reclaimCooldowns.get(uuid) - 1);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }


    public void addPlayerToMapReclaim(Player player, Integer time) {
        reclaimCooldowns.put(player.getUniqueId(), time);
    }

    public boolean isPlayerInMapReclaim(Player player) {
        return reclaimCooldowns.containsKey(player.getUniqueId());
    }

    public Integer getTimeRemainingReclaim(Player player) {
        if (!isPlayerInMapReclaim(player)) {
            return 0;
        } else {
            return reclaimCooldowns.get(player.getUniqueId());
        }
    }
}
