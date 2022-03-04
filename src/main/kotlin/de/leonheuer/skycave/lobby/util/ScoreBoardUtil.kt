package de.leonheuer.skycave.lobby.util

import de.leonheuer.skycave.lobby.SkyCaveLobby
import de.leonheuer.skycave.lobby.enums.Server
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.DisplaySlot

object ScoreBoardUtil {

    private val main = JavaPlugin.getPlugin(SkyCaveLobby::class.java)

    fun setScoreBoard(player: Player) {
        val board = Bukkit.getScoreboardManager().newScoreboard
        val obj = board.registerNewObjective("ScoreBoard", "dummy", Component.text("§r §f §r §f§lSky§3§lCave§b§l.de §r §f "))
        obj.displaySlot = DisplaySlot.SIDEBAR

        val counter = board.registerNewTeam("onlineCounter")
        counter.addEntry("§1")
        val playerCount = main.playerCount[Server.ALL] ?: 0
        counter.prefix(Component.text("§r §7 §r §f$playerCount§7/ §f100"))

        obj.getScore("§0").score = 9
        obj.getScore("§7§l▸ §bOnline:").score = 8
        obj.getScore("§1").score = 7
        obj.getScore("§2").score = 6
        obj.getScore("§7§l▸ §bForum:").score = 5
        obj.getScore("§r §7 §r §f§nskycave.de").score = 4
        obj.getScore("§3").score = 3
        obj.getScore("§7§l▸ §bDiscord:").score = 2
        obj.getScore("§r §7 §r §8/§fdiscord").score = 1
        obj.getScore("§5").score = 0

        player.scoreboard = board
    }

    fun updateScoreBoard(player: Player) {
        val team = player.scoreboard.getTeam("onlineCounter") ?: return
        val playerCount = main.playerCount[Server.ALL] ?: 0
        team.prefix(Component.text("§r §7 §r §f$playerCount"))
    }

}