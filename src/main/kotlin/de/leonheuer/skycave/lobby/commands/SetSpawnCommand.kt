package de.leonheuer.skycave.lobby.commands

import de.leonheuer.skycave.lobby.SkyCaveLobby
import de.leonheuer.skycave.lobby.enums.Message
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetSpawnCommand(private val main: SkyCaveLobby): CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            main.logger.severe("This command is for players only.")
            return true
        }

        main.dataManager.setSpawn(sender.location)
        main.dataManager.save()
        sender.sendMessage(Message.SET_SPAWN_SUCCESS.getMessage())
        return true
    }

}