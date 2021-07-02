package de.leonheuer.skycave.lobby.manager

import com.google.gson.GsonBuilder
import de.leonheuer.skycave.lobby.SkyCaveLobby
import de.leonheuer.skycave.lobby.models.SpawnLocation
import org.bukkit.Bukkit
import org.bukkit.Location
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*

class DataManager(private val main: SkyCaveLobby) {

    private lateinit var spawn: SpawnLocation
    private val path = "plugins/SkyCaveLobby"

    init {
        val dir = File(path)
        if (!dir.exists()) dir.mkdirs()

        val file = File("$path/spawn.json")
        if (!file.exists()) file.createNewFile()

        try {
            val builder = GsonBuilder()
            val gson = builder.create()
            val reader = BufferedReader(FileReader(file))
            spawn = gson.fromJson(reader, SpawnLocation::class.java)
        } catch (e: NullPointerException) {
            main.logger.warning("No data stored yet, nothing loaded into cache!")
        }
    }

    fun getSpawn(): Location {
        return Location(Bukkit.getWorld(UUID.fromString(spawn.uuid)), spawn.x, spawn.y, spawn.z, spawn.yaw, spawn.pitch)
    }

    fun setSpawn(loc: Location) {
        spawn = SpawnLocation(loc.world.uid.toString(), loc.x, loc.y, loc.z, loc.yaw, loc.pitch)
    }

    fun save() {
        val builder = GsonBuilder()
        builder.setPrettyPrinting()
        val gson = builder.create()
        val writer = FileWriter("$path/spawn.json")
        writer.write(gson.toJson(spawn))
        writer.flush()
    }

}