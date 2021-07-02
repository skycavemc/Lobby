package de.leonheuer.skycave.lobby.util

import de.leonheuer.skycave.lobby.SkyCaveLobby
import de.leonheuer.skycave.lobby.enums.GUI
import de.leonheuer.skycave.lobby.enums.LobbyItem
import de.leonheuer.skycave.lobby.models.StringPair
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

object GUIUtil {

    private val main = JavaPlugin.getPlugin(SkyCaveLobby::class.java)

    @Suppress("deprecation")
    fun showGUI(player: Player, gui: GUI) {
        player.playSound(player.location, Sound.UI_BUTTON_CLICK, 1F, 1F)

        when (gui) {
            GUI.COMPASS -> {
                val inv = Bukkit.createInventory(null, 45, "§cNavigator")

                createPlaceholders(inv, 1)
                createPlaceholders(inv, 5)

                val count = main.playerCount.skybee
                if (count == 0) count.inc()

                inv.setItem(19, getItemStack(LobbyItem.COMPASS_SPAWN, 1))
                inv.setItem(21, getItemStack(LobbyItem.COMPASS_SKYBEE, count,
                    StringPair("%count", "${main.playerCount.skybee}")))
                inv.setItem(23, getItemStack(LobbyItem.COMPASS_SOON, 1))
                inv.setItem(25, getItemStack(LobbyItem.COMPASS_SOON, 1))

                player.openInventory(inv)
            }
        }
    }

    private fun createPlaceholders(inv: Inventory, row: Int) {
        val placeholder = getItemStack("§0", 1, Material.BLACK_STAINED_GLASS_PANE, null)
        for (i in (row-1)*9 until row*9) {
            inv.setItem(i, placeholder)
        }
    }

    fun getItemStack(item: LobbyItem, amount: Int, vararg replace: StringPair): ItemStack {
        var description = item.description
        for (pair in replace) {
            description =  description.replace(pair.string1, pair.string2)
        }
        return getItemStack(item.title, amount, item.mat, description)
    }

    @Suppress("deprecation")
    fun getItemStack(title: String, amount: Int, mat: Material, description: String?): ItemStack {
        val item = ItemStack(mat, amount)
        val meta = item.itemMeta
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', title))
        if (description != null) {
            meta.lore = ChatColor.translateAlternateColorCodes('&', description).split("//")
        }
        item.itemMeta = meta
        return item
    }

    fun setUnbreakable(item: ItemStack): ItemStack {
        val meta = item.itemMeta
        meta.isUnbreakable = true
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        item.itemMeta = meta
        return item
    }

}