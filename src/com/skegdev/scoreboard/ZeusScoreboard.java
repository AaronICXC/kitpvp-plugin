package com.skegdev.scoreboard;

import com.skegdev.startup.ZeusInit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ZeusScoreboard {
    ZeusInit plugin;

    public ZeusScoreboard(ZeusInit zeusInit) {
        this.plugin = zeusInit;
    }

    public void createScoreboard(Player player) {

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective(ChatColor.BLUE + "Waffle" + ChatColor.GOLD + "PvP", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score emptyScore1 = objective.getScore("");
        emptyScore1.setScore(13);

        Score Player = objective.getScore(ChatColor.LIGHT_PURPLE + player.getName());
        Player.setScore(12);

        Score emptyScore5 = objective.getScore("");
        emptyScore5.setScore(11);

        Score Level;
        Level = objective.getScore(ChatColor.GOLD + "Level: " + ChatColor.YELLOW + player.getLevel());
        Level.setScore(9);

        Score emptyScore3 = objective.getScore("");
        emptyScore3.setScore(8);

        Score Kills = objective.getScore(ChatColor.AQUA + "Kills: " + player.getStatistic(Statistic.PLAYER_KILLS));
        Kills.setScore(7);

        Score Deaths = objective.getScore(ChatColor.RED + "Deaths: " + player.getStatistic(Statistic.DEATHS));
        Deaths.setScore(6);

        player.setScoreboard(scoreboard);
    }


}
