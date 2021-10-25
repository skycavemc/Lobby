package de.leonheuer.skycave.lobby.util

import de.leonheuer.skycave.lobby.SkyCaveLobby
import de.leonheuer.skycave.lobby.enums.GUI
import de.leonheuer.skycave.lobby.enums.LobbyItem
import de.leonheuer.skycave.lobby.models.CustomItem
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
        player.playSound(player.location, Sound.BLOCK_CHEST_OPEN, 1F, 1.5F)

        when (gui) {
            GUI.COMPASS -> {
                val inv = Bukkit.createInventory(null, 27, "§c§lNavigator")

                placeholdersByPattern(inv, 0, "cbcbcbcbc")
                placeholdersByPattern(inv, 2, "bcbcbcbcb")

                val count = main.playerCount.skybee
                var itemCount = count;
                if (count == 0) itemCount = 1

                inv.setItem(10, getLobbyItem(LobbyItem.COMPASS_SPAWN, 1))
                inv.setItem(12, getLobbyItem(LobbyItem.COMPASS_SKYBLOCK, itemCount,
                    StringPair("%count", "${main.playerCount.skybee}")))
                inv.setItem(14, getLobbyItem(LobbyItem.COMPASS_HARDCORE, 1))
                inv.setItem(16, getLobbyItem(LobbyItem.COMPASS_SOON, 1))

                player.openInventory(inv)
            }
        }
    }

    private fun placeholdersByPattern(inv: Inventory, startRow: Int, vararg pattern: String) {
        var lineCount = startRow * 9
        for (line in pattern) {
            if (line.length != 9) {
                continue
            }
            val parts = line.split("")
            var slot = lineCount
            for (identifier in parts) {
                if (identifier.length != 1) {
                    continue
                }
                val mat = patternToMaterial(identifier)
                if (mat != null) {
                    inv.setItem(slot, CustomItem(mat, 1).setName("§0").itemStack)
                }
                slot++
            }
            lineCount += 9
        }
    }

    private fun patternToMaterial(identifier: String): Material? {
        return when (identifier) {
            "n" -> null
            "w" -> Material.WHITE_STAINED_GLASS_PANE
            "0" -> Material.BLACK_STAINED_GLASS_PANE
            "g" -> Material.LIME_STAINED_GLASS_PANE
            "r" -> Material.RED_STAINED_GLASS_PANE
            "c" -> Material.CYAN_STAINED_GLASS_PANE
            "b" -> Material.BLUE_STAINED_GLASS_PANE
            else -> null
        }
    }

    fun getLobbyItem(item: LobbyItem, amount: Int, vararg replace: StringPair): ItemStack {
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