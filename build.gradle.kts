import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.to

plugins {
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "de.leonheuer.skycave"
version = "1.1.1"

repositories {
    mavenCentral()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("io.papermc.paper:paper-api:1.19-R0.1-SNAPSHOT")
}

tasks {
    test {
        useJUnit()
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveFileName.set("${project.name}-${project.version}.jar")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "de.leonheuer.skycave.lobby.SkyCaveLobby"))
        }
        dependencies {
            exclude(dependency("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT"))
        }
    }

    build {
        dependsOn(shadowJar)
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}