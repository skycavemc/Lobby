package de.leonheuer.skycave.lobby.listener.player

import com.google.common.io.ByteStreams
import de.leonheuer.skycave.lobby.SkyCaveLobby
import de.leonheuer.skycave.lobby.enums.Message
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InventoryClickListener(private val main: SkyCaveLobby): Listener {

    @Suppress("UnstableApiUsage")
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        event.isCancelled = true

        if (event.inventory == player.inventory) {
            return
        }

        val item = event.currentItem ?: return
        when (item.type) {
            Material.GRASS_BLOCK -> {
                val out = ByteStreams.newDataOutput()
                out.writeUTF("Connect")
                out.writeUTF("skybee")
                player.sendPluginMessage(main, "BungeeCord", out.toByteArray())
            }
            Material.MAGMA_CREAM -> {
                try {
                    player.teleport(main.dataManager.getSpawn())
                } catch (e: UninitializedPropertyAccessException) {
                    main.logger.warning("Spawn point has not been set!")
                }
                player.sendMessage(Message.SPAWN_SUCCESS.getMessage())
                player.playSound(player.location, Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 1F)
            }
            Material.FIREWORK_ROCKET -> player.playSound(player.location, Sound.ENTITY_ITEM_BREAK, 1F, 1F)
            Material.FIREWORK_STAR -> player.playSound(player.location, Sound.ENTITY_ITEM_BREAK, 1F, 1F)
            else -> return
        }
    }

}