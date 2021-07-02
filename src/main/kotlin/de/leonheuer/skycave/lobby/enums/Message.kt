package de.leonheuer.skycave.lobby.enums

import org.bukkit.ChatColor

enum class Message(private val message: String) {

    PREFIX("&e&l| &6Lobby &8» "),
    BLOCKED("&cDieser Befehl wurde blockiert."),

    // set spawn command
    SET_SPAWN_SUCCESS("&aDer Spawnpunkt wurde erfolgreich geändert."),

    // spawn command
    SPAWN_SUCCESS("&7Du hast dich zum Spawn teleportiert."),
    ;

    fun getMessage(): String {
        return ChatColor.translateAlternateColorCodes('&', PREFIX.message + message)
    }

}