package de.leonheuer.skycave.lobby.models

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

@Suppress("unused")
class ItemBuilder private constructor(material: Material) {

    val item: ItemStack

    init {
        item = ItemStack(material, 1)
    }

    companion object {
        fun of(material: Material): ItemBuilder {
            return ItemBuilder(material)
        }
    }

    fun title(title: Component): ItemBuilder {
        item.editMeta { it.displayName(title) }
        return this
    }

    fun lore(vararg lore: Component): ItemBuilder {
        lore(lore.asList())
        return this
    }

    fun lore(lore: List<Component>): ItemBuilder {
        item.editMeta { it.lore(lore) }
        return this
    }

    fun flags(vararg flags: ItemFlag): ItemBuilder {
        item.addItemFlags(*flags)
        return this
    }

    fun unbreakable(): ItemBuilder {
        item.editMeta { it.isUnbreakable = true }
        return this
    }

    fun enchant(enchant: Enchantment, level: Int): ItemBuilder {
        item.editMeta { it.addEnchant(enchant, level, true) }
        return this
    }

}