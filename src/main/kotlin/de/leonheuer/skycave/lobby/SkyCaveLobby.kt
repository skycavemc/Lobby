package de.leonheuer.skycave.lobby

import de.leonheuer.skycave.lobby.commands.SetSpawnCommand
import de.leonheuer.skycave.lobby.commands.SpawnCommand
import de.leonheuer.skycave.lobby.listener.*
import de.leonheuer.skycave.lobby.manager.DataManager
import de.leonheuer.skycave.lobby.models.Invisibility
import de.leonheuer.skycave.lobby.models.PlayerCount
import de.leonheuer.skycave.lobby.tasks.PlayerCountTask
import de.leonheuer.skycave.lobby.util.ScoreBoardUtil
import de.leonheuer.skycave.lobby.util.TabListUtil
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class SkyCaveLobby : JavaPlugin() {

    lateinit var dataManager: DataManager
        private set
    lateinit var playerCount: PlayerCount
        private set
    lateinit var invisibility: Invisibility
        private set

    @Suppress("Deprecation")
    override fun onEnable() {
        dataManager = DataManager(this)
        playerCount = PlayerCount(0, 0)
        invisibility = Invisibility(ArrayList())

        val scheduler = Bukkit.getScheduler()
        scheduler.scheduleAsyncRepeatingTask(this, PlayerCountTask(this), 20L, 20L)
        scheduler.scheduleAsyncRepeatingTask(this, { Bukkit.getOnlinePlayers().forEach(ScoreBoardUtil::updateScoreBoard) }, 20L, 20L)
        scheduler.scheduleAsyncRepeatingTask(this, { TabListUtil.setTabList() }, 0L, 30L)

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

        getCommand("setspawn")!!.setExecutor(SetSpawnCommand(this))
        getCommand("spawn")!!.setExecutor(SpawnCommand(this))
    }

}