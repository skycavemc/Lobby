package de.leonheuer.skycave.lobby.util

import de.leonheuer.skycave.lobby.SkyCaveLobby
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

object InvisibilityUtil {

    private val main = JavaPlugin.getPlugin(SkyCaveLobby::class.java)

    fun hideAllPlayersFor(player: Player) {
        Bukkit.getOnlinePlayers().forEach {
            if (it != player) player.hidePlayer(main, it)
        }
    }

    fun showAllPlayersFor(player: Player) {
        Bukkit.getOnlinePlayers().forEach {
            if (it != player) player.showPlayer(main, it)
        }
    }

    fun hideForAllPlayers(player: Player) {
        Bukkit.getOnlinePlayers().forEach {
            if (main.invisible.contains(it) && it != player) {
                it.hidePlayer(main, player)
            }
        }
    }

}