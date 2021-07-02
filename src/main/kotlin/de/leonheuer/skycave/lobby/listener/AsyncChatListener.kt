package de.leonheuer.skycave.lobby.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.*

class AsyncChatListener: Listener {

    @Suppress("Deprecation")
    @EventHandler
    fun onAsyncPlayerChat(event: AsyncPlayerChatEvent) {
        event.format = "§a${event.player.name}§8: §7${event.message}"
    }

}