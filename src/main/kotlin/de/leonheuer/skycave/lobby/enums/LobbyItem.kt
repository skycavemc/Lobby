package de.leonheuer.skycave.lobby.enums

import org.bukkit.Material

enum class LobbyItem(val title: String, val mat: Material, val description: String) {

    COMPASS("&c&lNavigator &8[&7Rechtsklick&8]", Material.COMPASS,
        "&r//&7&oEine Übersicht, mithilfe der du dich mit//&7&ounseren Spielmodi verbinden kannst"),

    COMPASS_SPAWN("&e&lSpawn", Material.MAGMA_CREAM, "&r//&7&oTeleportiert dich zurück zum Spawn"),

    COMPASS_SKYBEE("&6&lSkyBee", Material.GRASS_BLOCK,
        "&r//&7&oErbaue dein eigenes Inselreich,//&7&oalleine oder mit Freunden//&r//&a&lOnline&8: &e%count Spieler"),

    COMPASS_SKYPARTY("&d&lSkyParty", Material.FIREWORK_ROCKET,
        "&r//&7&oSpiele mit 2 bis 8 Spielern//&7&oauf einem großen Spielfeld.//" +
                "&7&oNach jeder Würfelrunde//&7&ogibt es ein Minispiel.//&7&oDer erste im Ziel gewinnt!//&r//&c&lIn der Entwicklung"),

    COMPASS_SOON("&7Ein geheimnisvolles Item", Material.FIREWORK_STAR, "&cWird noch entlüftet!"),

    GRAPPLER("&6&lGrappler &8[&7Rechtsklick&8]", Material.FISHING_ROD, "&r//&7&oEin Enterhaken, der dich zum//&7&oZiel befördert."),

    SPEED_ON("&b&lSpeed-Boost &8[&7aktiviert&8]", Material.FEATHER, "&r//&7&oSchaltet erhöhte Geschwindigkeit//&7&oan und aus."),

    SPEED_OFF("&b&lSpeed-Boost &8[&7deaktiviert&8]", Material.GUNPOWDER, "&r//&7&oSchaltet erhöhte Geschwindigkeit//&7&oan und aus."),

    VISIBLE_ALL("&a&lSpieler &8[&7sichtbar&8]", Material.LIME_DYE, "&r//&7&oSchaltet die Sichtbarkeit//&7&oanderer Spieler um."),

    VISIBLE_NONE("&a&lSpieler &8[&7unsichtbar&8]", Material.GRAY_DYE, "&r//&7&oSchaltet die Sichtbarkeit//&7&oanderer Spieler um."),
    ;

}