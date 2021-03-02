val kotlin_version: String by extra
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}
apply {
    plugin("kotlin-android")
}


android {

    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "com.apro.mipSAR2sens"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        project.ext.set("archivesBaseName", "mipSAR2sens-" + defaultConfig.versionName)
        vectorDrawables.useSupportLibrary = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    flavorDimensions("default")


}


dependencies {

    implementation(project(":core-ui"))

    implementation( files("libs/d2xx.jar"))

    implementation(Libs.kotlin)
    implementation(Libs.ktx)
    implementation(Libs.appCompat)
    implementation(Libs.constraintLayout)

    implementation(Libs.material)

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)

    implementation(Libs.delegates)

    implementation(Libs.timber)

    api(Libs.cicerone)

    implementation(Libs.navigationFragment)
    implementation(Libs.navigationUi)

    implementation(Libs.roomRuntime)
    implementation(Libs.roomKtx)
    kapt(Libs.roomCompiler)

    implementation(Libs.retrofit)
    implementation(Libs.retrofitGsonConverter)
    implementation(Libs.okHttpLoggingInterceptor)

    implementation(Libs.glide)
    kapt(Libs.glideCompiler)
    implementation(Libs.glideTransformations)


}

repositories {
    mavenCentral()
}