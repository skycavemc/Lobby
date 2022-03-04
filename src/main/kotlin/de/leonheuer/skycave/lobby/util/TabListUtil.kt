package de.leonheuer.skycave.lobby.util

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit

object TabListUtil {

    private var changed = false

    fun setTabList() {
        if (changed) {
            Bukkit.getOnlinePlayers().forEach {
                it.sendPlayerListHeaderAndFooter(
                    Component.text("§f§lSky§3§lCave§b§l.de\n §8» §fDein §bSkyBlock §eNetzwerk! §8« \n"),
                    Component.text("\n§7✦ §eForum: §f§nskycave.de§r §7✦")
                )
            }
        } else {
            Bukkit.getOnlinePlayers().forEach {
                it.sendPlayerListHeaderAndFooter(
                    Component.text("§f§lSky§3§lCave§b§l.de\n §8» §7Dein §9SkyBlock §6Netzwerk! §8« \n"),
                    Component.text("\n§7✦ §6Forum: §e§nskycave.de§r §7✦")
                )
            }
        }
        changed = !changed
    }

}