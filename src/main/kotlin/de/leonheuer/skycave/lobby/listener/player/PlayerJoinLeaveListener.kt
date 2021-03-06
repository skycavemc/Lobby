package de.leonheuer.skycave.lobby.listener.player

import de.leonheuer.skycave.lobby.SkyCaveLobby
import de.leonheuer.skycave.lobby.enums.LobbyItem
import de.leonheuer.skycave.lobby.util.GUIUtil
import de.leonheuer.skycave.lobby.util.InvisibilityUtil
import de.leonheuer.skycave.lobby.util.ScoreBoardUtil
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerRespawnEvent
import java.time.Duration

@Suppress("unused")
class PlayerJoinLeaveListener(private val main: SkyCaveLobby) : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        event.joinMessage(null)
        resetPlayer(player)
        player.showTitle(Title.title(
            Component.text("§8» §6Willkommen §8«"),
            Component.text(" §7in der §3SkyCave §eLobby"),
            Title.Times.times(
                Duration.ofMillis(500),
                Duration.ofSeconds(2),
                Duration.ofSeconds(1)
            )
        ))
        ScoreBoardUtil.setScoreBoard(player)

        try {
            player.teleport(main.dataManager.getSpawn())
        } catch (e: UninitializedPropertyAccessException) {
            main.logger.warning("Spawn point has not been set!")
        } catch (e: IllegalArgumentException) {
            main.logger.warning("Spawn point has not been set!")
        }
    }

    @EventHandler
    fun onPlayerRespawn(event: PlayerRespawnEvent) {
        val player = event.player
        resetPlayer(player)

        try {
            event.respawnLocation = main.dataManager.getSpawn()
        } catch (e: UninitializedPropertyAccessException) {
            main.logger.warning("Spawn point has not been set!")
        } catch (e: IllegalArgumentException) {
            main.logger.warning("Spawn point has not been set!")
        }
    }

    private fun resetPlayer(player: Player) {
        player.health = 20.0
        player.foodLevel = 20
        player.exhaustion = 0F
        player.saturation = 200F
        player.activePotionEffects.clear()
        player.exp = 0F
        player.level = 0
        player.gameMode = GameMode.SURVIVAL
        player.walkSpeed = 0.2f
        player.flySpeed = 0.2f

        val inv = player.inventory
        inv.clear()
        inv.setItem(1, GUIUtil.getLobbyItem(LobbyItem.COMPASS, 1))
        inv.setItem(3, GUIUtil.setUnbreakable(GUIUtil.getLobbyItem(LobbyItem.GRAPPLER, 1)))
        inv.setItem(5, GUIUtil.getLobbyItem(LobbyItem.SPEED_OFF, 1))
        inv.setItem(7, GUIUtil.getLobbyItem(LobbyItem.VISIBLE_ALL, 1))

        InvisibilityUtil.hideForAllPlayers(player)
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        event.quitMessage(null)
    }

    @EventHandler
    fun onPlayerKick(event: PlayerKickEvent) {
        event.leaveMessage(Component.text(""))
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerDeathEvent) {
        event.deathMessage(null)
    }

}