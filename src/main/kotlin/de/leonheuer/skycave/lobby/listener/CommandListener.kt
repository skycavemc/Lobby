package de.leonheuer.skycave.lobby.listener

import de.leonheuer.skycave.lobby.enums.Command
import de.leonheuer.skycave.lobby.enums.Message
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class CommandListener: Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onCommand(event: PlayerCommandPreprocessEvent) {
        val player = event.player
        val cmd = event.message.split(" ")[0]
        val map = Bukkit.getCommandMap()

        if (player.hasPermission("skybee.commandblocker.bypass")) {
            return
        }
        if (map.getCommand(cmd.replace("/", "")) == null) {
            return
        }

        if (cmd.contains(":")) {
            val partial = cmd.split(":")
            if (!partial[0].contains(" ")) {
                event.isCancelled = true
                player.sendMessage(Message.BLOCKED.getMessage())
            }
            return
        }

        for (blocked in Command.values()) {
            if (cmd.lowercase().startsWith("/${blocked.content}")) {
                event.isCancelled = true
                player.sendMessage(Message.BLOCKED.getMessage())
            }
        }
    }
}