// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    var kotlin_version: String by extra
    kotlin_version = "1.3.21"
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.3.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.21")
//       classpath(kotlinModule("gradle-plugin", kotlin_version))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}

