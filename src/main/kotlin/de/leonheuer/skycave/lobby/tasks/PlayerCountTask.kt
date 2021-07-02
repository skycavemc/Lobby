package de.leonheuer.skycave.lobby.tasks

import com.google.common.collect.Iterables
import com.google.common.io.ByteStreams
import de.leonheuer.skycave.lobby.SkyCaveLobby
import org.bukkit.Bukkit

@Suppress("UnstableApiUsage")
class PlayerCountTask(private val main: SkyCaveLobby): Runnable {

    override fun run() {
        if (Bukkit.getOnlinePlayers().isEmpty()) {
            return
        }

        val player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null)

        var out = ByteStreams.newDataOutput()
        out.writeUTF("PlayerCount")
        out.writeUTF("ALL")
        player!!.sendPluginMessage(main, "BungeeCord", out.toByteArray())

        out = ByteStreams.newDataOutput()
        out.writeUTF("PlayerCount")
        out.writeUTF("skybee")
        player.sendPluginMessage(main, "BungeeCord", out.toByteArray())
    }

}