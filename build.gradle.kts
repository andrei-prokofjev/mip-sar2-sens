// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.4.30"
    val kotlinVersion by extra("1.4.30")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath(BuildPlugins.androidGradle)
        classpath(BuildPlugins.navigationSafeArgsGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")

    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}
