package com.skegdev.cooldownmanager;

import com.skegdev.startup.ZeusInit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManagerReclaimMonthly {
    private Map<UUID, Long> reclaimCooldowns = new HashMap<>();

    public CooldownManagerReclaimMonthly(ZeusInit plugin) {
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


    public void addPlayerToMapReclaim(Player player, Long time) {
        reclaimCooldowns.put(player.getUniqueId(), time);
    }

    public boolean isPlayerInMapReclaim(Player player) {
        return reclaimCooldowns.containsKey(player.getUniqueId());
    }
}
