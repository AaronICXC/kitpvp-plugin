package com.skegdev.events;

import com.skegdev.startup.ZeusInit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class EventManager implements Listener {
    ZeusInit zeusInit;

    public EventManager(ZeusInit zeusInit) {
        this.zeusInit = zeusInit;
    }

    @EventHandler
    public void onHitAbilities(EntityDamageByEntityEvent hitEvent) {

        if (hitEvent.getEntity() instanceof Player && hitEvent.getDamager() instanceof Player && !hitEvent.isCancelled()) {
            Player attacker = (Player) hitEvent.getDamager();
            Player defender = (Player) hitEvent.getEntity();
            ItemStack hand = attacker.getInventory().getItemInHand();

            if (hand.getType() == Material.ARROW && !(zeusInit.getArcherCooldowns().isPlayerInMapArcher(attacker))) {
                defender.addPotionEffect(PotionEffectType.SLOW.createEffect(400, 5));
                attacker.sendMessage(ChatColor.AQUA + "You used " + ChatColor.BOLD + "Anti-Speed" + ChatColor.AQUA + " on " + ChatColor.BOLD + defender.getName());
                defender.sendMessage(ChatColor.RED + "You got hit with an " + ChatColor.BOLD + "Anti-Speed" + ChatColor.RED + " Arrow from " + ChatColor.BOLD + attacker.getName());
                zeusInit.getArcherCooldowns().addPlayerToMapArcher(attacker, 50);
            } else if (hand.getType() == Material.ARROW && zeusInit.getArcherCooldowns().isPlayerInMapArcher(attacker)) {
                attacker.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot use this ability for " + zeusInit.getArcherCooldowns().getTimeRemainingArcher(attacker) + " seconds.");
            }

            if (hand.getType() == Material.FEATHER) {
                if (!(zeusInit.getMadmanCooldowns().isPlayerInMapMadman(attacker))) {
                    defender.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(400, 1));
                    defender.addPotionEffect(PotionEffectType.CONFUSION.createEffect(800, 0));
                    defender.addPotionEffect(PotionEffectType.SLOW.createEffect(800, 2));
                    attacker.sendMessage(ChatColor.AQUA + "You used the " + ChatColor.BOLD + "Amalgamation of Disorientation" + ChatColor.AQUA + " on " + ChatColor.BOLD + defender.getName());
                    defender.sendMessage(ChatColor.RED + "You got hit with an " + ChatColor.BOLD + "Amalgamation of Disorientation" + ChatColor.RED + " Feather from " + ChatColor.BOLD + attacker.getName());
                    zeusInit.getMadmanCooldowns().addPlayerToMapMadman(attacker, 50);
                } else if (zeusInit.getMadmanCooldowns().isPlayerInMapMadman(attacker)) {
                    attacker.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot use this ability for " + zeusInit.getMadmanCooldowns().getTimeRemainingMadman(attacker) + " seconds");
                }
            }

            if (hand.getType() == Material.GHAST_TEAR) {
                if (!(zeusInit.getVampireCooldowns().isPlayerInMapVampire(attacker))) {
                    if (defender.getHealth() > defender.getHealth() - 10.0) {
                        defender.setHealth(defender.getHealth() - 10.0);
                        attacker.sendMessage(ChatColor.AQUA + "You used the " + ChatColor.BOLD + "Vampire's Tooth" + ChatColor.AQUA + " on " + ChatColor.BOLD + defender.getName());
                        defender.sendMessage(ChatColor.RED + "You got hit with a " + ChatColor.BOLD + "Vampire's Tooth" + ChatColor.RED + " from " + ChatColor.BOLD + attacker.getName());
                        zeusInit.getVampireCooldowns().addPlayerToMapVampire(attacker, 40);
                    } else if (defender.getHealth() < defender.getHealth() - 10.0) {
                        defender.setHealth(0);
                        attacker.sendMessage(ChatColor.AQUA + "You used the " + ChatColor.BOLD + "Vampire's Tooth" + ChatColor.AQUA + " on " + ChatColor.BOLD + defender.getName());
                        defender.sendMessage(ChatColor.RED + "You got hit with a " + ChatColor.BOLD + "Vampire's Tooth" + ChatColor.RED + " from " + ChatColor.BOLD + attacker.getName());
                        zeusInit.getVampireCooldowns().addPlayerToMapVampire(attacker, 40);
                    }

                } else if (zeusInit.getVampireCooldowns().isPlayerInMapVampire(attacker)) {
                    attacker.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot use this ability for " + zeusInit.getVampireCooldowns().getTimeRemainingVampire(attacker) + " seconds");
                }
            }
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent interactEvent) {
        Player user = interactEvent.getPlayer();
        if (interactEvent.getPlayer().getInventory().getItemInHand().getType() == Material.BEACON) {
            interactEvent.setCancelled(true);

            if (!(zeusInit.getTankCooldowns().isPlayerInMapTank(user))) {
                if (interactEvent.getAction() == Action.RIGHT_CLICK_AIR || interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    user.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(400, 0));
                    user.addPotionEffect(PotionEffectType.ABSORPTION.createEffect(400, 0));
                    user.addPotionEffect(PotionEffectType.SLOW.createEffect(800, 4));
                    user.sendMessage(ChatColor.AQUA + "You used " + ChatColor.BOLD + "Tank's Shell!");
                    zeusInit.getTankCooldowns().addPlayerToMapTank(user, 50);
                }
            } else if (zeusInit.getTankCooldowns().isPlayerInMapTank(user)) {
                if (interactEvent.getAction() == Action.RIGHT_CLICK_AIR || interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    user.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot use this ability for " + zeusInit.getTankCooldowns().getTimeRemainingTank(user) + " seconds");
                }
            }
        }

        if (interactEvent.getPlayer().getInventory().getItemInHand().getType() == Material.REDSTONE) {
            interactEvent.setCancelled(true);

            if (!(zeusInit.getBerserkerCooldowns().isPlayerInMapBerserker(user))) {
                if (interactEvent.getAction() == Action.RIGHT_CLICK_AIR || interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    user.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(200, 0));
                    user.addPotionEffect(PotionEffectType.SPEED.createEffect(400, 1));
                    user.sendMessage(user.getNearbyEntities(15,15,15).toString());
                    zeusInit.getBerserkerCooldowns().addPlayerToMapBerserker(user, 50);
                    Player entities = (Player) user.getNearbyEntities(15, 15, 15);
                    entities.addPotionEffect(PotionEffectType.SLOW.createEffect(200, 1));
                    entities.addPotionEffect(PotionEffectType.WEAKNESS.createEffect(300, 1));
                    user.sendMessage(ChatColor.AQUA + "You used " + ChatColor.BOLD + "War Cry!");
                    entities.sendMessage(ChatColor.RED + "You got caught in a " + ChatColor.BOLD + "War Cry" + ChatColor.RED + " from " + ChatColor.BOLD + user.getName());
                    zeusInit.getBerserkerCooldowns().addPlayerToMapBerserker(user, 50);
                }
            } else if (zeusInit.getBerserkerCooldowns().isPlayerInMapBerserker(user)) {
                if (interactEvent.getAction() == Action.RIGHT_CLICK_AIR || interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    user.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot use this ability for " + zeusInit.getBerserkerCooldowns().getTimeRemainingBerserker(user) + " seconds");
                }
            }
        }
    }
}