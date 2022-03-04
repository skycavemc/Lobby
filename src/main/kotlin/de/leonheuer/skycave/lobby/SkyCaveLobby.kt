package de.leonheuer.skycave.lobby

import de.leonheuer.skycave.lobby.commands.SetSpawnCommand
import de.leonheuer.skycave.lobby.commands.SpawnCommand
import de.leonheuer.skycave.lobby.enums.Server
import de.leonheuer.skycave.lobby.listener.*
import de.leonheuer.skycave.lobby.listener.player.*
import de.leonheuer.skycave.lobby.manager.DataManager
import de.leonheuer.skycave.lobby.tasks.PlayerCountTask
import de.leonheuer.skycave.lobby.util.ScoreBoardUtil
import de.leonheuer.skycave.lobby.util.TabListUtil
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.EnumMap

class SkyCaveLobby : JavaPlugin() {

    lateinit var dataManager: DataManager
        private set
    val invisible = ArrayList<Player>()
    val playerCount = EnumMap<Server, Int>(Server::class.java)

    override fun onEnable() {
        dataManager = DataManager(this)

        val scheduler = Bukkit.getScheduler()
        scheduler.runTaskTimerAsynchronously(this,
            PlayerCountTask(this), 20L, 20L)
        scheduler.runTaskTimerAsynchronously(this,
            Runnable { Bukkit.getOnlinePlayers().forEach(ScoreBoardUtil::updateScoreBoard) }, 20L, 20L)
        scheduler.runTaskTimerAsynchronously(this,
            Runnable { TabListUtil.setTabList() }, 0L, 30L)

        val pm = server.pluginManager
        pm.registerEvents(AsyncChatListener(), this)
        pm.registerEvents(PlayerFishListener(), this)
        pm.registerEvents(PlayerJoinLeaveListener(this), this)
        pm.registerEvents(ProtectionListener(), this)
        pm.registerEvents(PlayerInteractListener(this), this)
        pm.registerEvents(InventoryClickListener(this), this)
        pm.registerEvents(CommandListener(), this)

        server.messenger.registerIncomingPluginChannel(this, "BungeeCord", PluginMessageReceiver(this))
        server.messenger.registerOutgoingPluginChannel(this, "BungeeCord")

        registerCommand("setspawn", SetSpawnCommand(this))
        registerCommand("spawn", SpawnCommand(this))
    }

    private fun registerCommand(cmd: String, executor: CommandExecutor) {
        val command = getCommand(cmd)
        if (command == null) {
            logger.severe("No entry found in the plugin.yml for command $cmd")
            return
        }
        command.setExecutor(executor)
    }

}