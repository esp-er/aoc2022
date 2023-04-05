plugins {
    kotlin("jvm") version "1.8.20"
    application
}

repositories {
    mavenCentral()
}

kotlin {
    sourceSets.all {
        languageSettings {
            languageVersion = "2.0"
        }
    }
}


application {
    mainClass.set("patriker.day20.Day20Kt")
}



tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}
