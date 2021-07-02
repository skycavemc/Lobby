package de.leonheuer.skycave.lobby.listener

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerAttemptPickupItemEvent
import org.bukkit.event.player.PlayerDropItemEvent

class ProtectionListener: Listener {

    @EventHandler
    fun onPlayerDropItem(event: PlayerDropItemEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerPickupItem(event: PlayerAttemptPickupItemEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onFoodLevelChange(event: FoodLevelChangeEvent) {
        val player = event.entity as Player
        player.foodLevel = 20
        player.saturation = 200F
    }

}