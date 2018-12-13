
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
        compileSdkVersion(28)

        defaultConfig {
            applicationId = "com.danshima.noodleapp"
            minSdkVersion(21)
            targetSdkVersion(28)
            versionCode = 1
            versionName = "1.0"
            testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        }
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
        }
}

dependencies {
        implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
        implementation("com.android.support:appcompat-v7:28.0.0")
        implementation("com.android.support.constraint:constraint-layout:1.1.3")
        implementation("com.android.support:support-v4:28.0.0")
        implementation("com.android.support:recyclerview-v7:28.0.0")
        testImplementation("junit:junit:4.12")
        androidTestImplementation("com.android.support.test:runner:1.0.2")
        androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
        implementation("com.android.support:design:28.0.0")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.10")
}
repositories {
        mavenCentral()
}

