package de.leonheuer.skycave.lobby.listener

import org.bukkit.Location
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.util.Vector

class PlayerFishListener: Listener {

    @EventHandler
    fun onPlayerFish(event: PlayerFishEvent) {
        val player = event.player
        val hook = event.hook
        val loc = hook.location

        if (solidBlockNearby(loc)) {
            val playerLoc = player.location
            player.velocity = Vector(
                loc.x - playerLoc.x,
                (loc.y - playerLoc.y) * 0.5,
                loc.z - playerLoc.z
            )
        }
    }

    private fun solidBlockNearby(loc: Location): Boolean {
        BlockFace.values().forEach {
            if (loc.block.getRelative(it).type.isSolid) return true
        }
        return false
    }

}