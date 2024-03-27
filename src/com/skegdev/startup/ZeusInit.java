package com.skegdev.startup;

import com.skegdev.commands.ReclaimCommand;
import com.skegdev.commands.RefillCommand;
import com.skegdev.cooldownmanager.*;
import com.skegdev.events.*;
import com.skegdev.kits.OpenSelector;
import com.skegdev.scoreboard.ZeusScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public class ZeusInit extends JavaPlugin {

    CooldownManager cooldowns;
    CooldownManagerRefill refillCooldowns;
    CooldownManagerArcher archerCooldowns;
    CooldownManagerTank tankCooldowns;
    CooldownManagerMadman madmanCooldowns;
    CooldownManagerBerserker berserkerCooldowns;
    CooldownManagerVampire vampireCooldowns;
    CooldownManagerReclaimDaily reclaimCooldownsDaily;
    CooldownManagerReclaimWeekly reclaimCooldownsWeekly;
    CooldownManagerReclaimMonthly reclaimCooldownsMonthly;
    ZeusScoreboard zeusScoreboard;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "(!) Zeus is starting up! (!)");
        cooldowns = new CooldownManager(this);
        refillCooldowns = new CooldownManagerRefill(this);
        archerCooldowns = new CooldownManagerArcher(this);
        tankCooldowns = new CooldownManagerTank(this);
        madmanCooldowns = new CooldownManagerMadman(this);
        berserkerCooldowns = new CooldownManagerBerserker(this);
        vampireCooldowns = new CooldownManagerVampire(this);
        reclaimCooldownsDaily = new CooldownManagerReclaimDaily(this);
        reclaimCooldownsWeekly = new CooldownManagerReclaimWeekly(this);
        reclaimCooldownsMonthly = new CooldownManagerReclaimMonthly(this);
        zeusScoreboard = new ZeusScoreboard(this);

        // Commands
        OpenSelector os = new OpenSelector();
        CooldownReset cr = new CooldownReset(this);
        RefillCommand rc = new RefillCommand(this);
        ReclaimCommand rc2 = new ReclaimCommand(this);

        getServer().getPluginCommand("kit").setExecutor(os);
        getServer().getPluginCommand("resetcooldown").setExecutor(cr);
        getServer().getPluginCommand("refill").setExecutor(rc);
        getServer().getPluginCommand("reclaim").setExecutor(rc2);

        // Events
        JoinEvent joinEvent = new JoinEvent(this);
        SoupEvent soupEvent = new SoupEvent();
        GUIEvents guiEvents = new GUIEvents(this);
        DeathEvent deathEvent = new DeathEvent();
        EventManager eventManager = new EventManager(this);

        getServer().getPluginManager().registerEvents(joinEvent, this);
        getServer().getPluginManager().registerEvents(soupEvent, this);
        getServer().getPluginManager().registerEvents(guiEvents, this);
        getServer().getPluginManager().registerEvents(deathEvent, this);
        getServer().getPluginManager().registerEvents(eventManager, this);

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "(!) Zeus is shutting down! (!)");
    }

    public CooldownManager getCooldownManager() {
        return cooldowns;
    }

    public CooldownManagerRefill getRefillCooldowns() {
        return refillCooldowns;
    }

    public CooldownManagerArcher getArcherCooldowns() {
        return archerCooldowns;
    }

    public CooldownManagerTank getTankCooldowns() {
        return tankCooldowns;
    }

    public CooldownManagerMadman getMadmanCooldowns() {
        return madmanCooldowns;
    }

    public CooldownManagerBerserker getBerserkerCooldowns() {
        return berserkerCooldowns;
    }

    public CooldownManagerVampire getVampireCooldowns() {
        return vampireCooldowns;
    }

    public CooldownManagerReclaimDaily getReclaimCooldownsDaily() {
        return reclaimCooldownsDaily;
    }

    public CooldownManagerReclaimWeekly getReclaimCooldownsWeekly() {
        return reclaimCooldownsWeekly;
    }

    public CooldownManagerReclaimMonthly getReclaimCooldownsMonthly() {
        return reclaimCooldownsMonthly;
    }

    public ZeusScoreboard getZeusScoreboard() {
        return zeusScoreboard;
    }

}
