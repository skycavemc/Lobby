package de.leonheuer.skycave.lobby.util

import org.bukkit.Bukkit

object TabListUtil {

    private var changed = false

    @Suppress("deprecation")
    fun setTabList() {
        changed = if (changed) {
            Bukkit.getOnlinePlayers().forEach { it.setPlayerListHeaderFooter("§f§lSky§3§lCave§b§l.de\n §8» §fDein §bSkyBlock §eNetzwerk! §8« \n",
                "\n§7✦ §eForum: §f§nskycave.de§r §7✦") }
            false
        } else {
            Bukkit.getOnlinePlayers().forEach { it.setPlayerListHeaderFooter("§f§lSky§3§lCave§b§l.de\n §8» §7Dein §9SkyBlock §6Netzwerk! §8« \n",
                "\n§7✦ §6Forum: §e§nskycave.de§r §7✦") }
            true
        }
    }

}