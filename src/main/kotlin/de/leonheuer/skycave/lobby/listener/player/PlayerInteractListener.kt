package de.leonheuer.skycave.lobby.listener.player

import de.leonheuer.skycave.lobby.SkyCaveLobby
import de.leonheuer.skycave.lobby.enums.GUI
import de.leonheuer.skycave.lobby.enums.LobbyItem
import de.leonheuer.skycave.lobby.util.GUIUtil
import de.leonheuer.skycave.lobby.util.InvisibilityUtil
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class PlayerInteractListener(private val main: SkyCaveLobby): Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = event.item ?: return

        when (item.type) {
            Material.COMPASS -> {
                event.isCancelled = true
                GUIUtil.showGUI(player, GUI.COMPASS)
            }
            Material.GUNPOWDER -> {
                event.isCancelled = true
                player.walkSpeed = 0.5f
                player.flySpeed = 0.5f
                player.inventory.setItemInMainHand(GUIUtil.getLobbyItem(LobbyItem.SPEED_ON, 1))
            }
            Material.FEATHER -> {
                event.isCancelled = true
                player.walkSpeed = 0.2f
                player.flySpeed = 0.2f
                player.inventory.setItemInMainHand(GUIUtil.getLobbyItem(LobbyItem.SPEED_OFF, 1))
            }
            Material.LIME_DYE -> {
                event.isCancelled = true
                main.invisible.add(player)
                InvisibilityUtil.hideAllPlayersFor(player)
                player.inventory.setItemInMainHand(GUIUtil.getLobbyItem(LobbyItem.VISIBLE_NONE, 1))
            }
            Material.GRAY_DYE -> {
                event.isCancelled = true
                main.invisible.remove(player)
                InvisibilityUtil.showAllPlayersFor(player)
                player.inventory.setItemInMainHand(GUIUtil.getLobbyItem(LobbyItem.VISIBLE_ALL, 1))
            }
            else -> return
        }
    }

}