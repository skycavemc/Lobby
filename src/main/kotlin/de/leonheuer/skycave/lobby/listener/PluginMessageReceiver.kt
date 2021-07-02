package de.leonheuer.skycave.lobby.listener

import com.google.common.io.ByteStreams
import de.leonheuer.skycave.lobby.SkyCaveLobby
import org.bukkit.entity.Player
import org.bukkit.plugin.messaging.PluginMessageListener

class PluginMessageReceiver(private val main: SkyCaveLobby): PluginMessageListener {

    @Suppress("UnstableApiUsage")
    override fun onPluginMessageReceived(channel: String, player: Player, message: ByteArray) {
        if (channel != "BungeeCord") {
            return
        }

        val input = ByteStreams.newDataInput(message)
        val sub = input.readUTF()

        if (sub.equals("PlayerCount")) {
            when (input.readUTF()) {
                "ALL" -> main.playerCount.all = input.readInt()
                "skybee" -> main.playerCount.skybee = input.readInt()
            }
        }
    }

}