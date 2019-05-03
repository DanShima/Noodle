plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}
apply {
    plugin("kotlin-android")
    plugin("kotlin-android-extensions")
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
        val roomVersion = "2.1.0-alpha07"
        val lifecycleVersion = "2.0.0"
        val daggerVersion = "2.18"
        val supportLibVersion = "1.0.0"
        val coroutineVersion = "1.2.0"

        implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
        // Room
        implementation("androidx.room:room-runtime:$roomVersion")
        kapt("androidx.room:room-compiler:$roomVersion")
        testImplementation("androidx.room:room-testing:$roomVersion")
        // Lifecycle
        implementation("android.arch.lifecycle:extensions:$lifecycleVersion")
        kapt("android.arch.lifecycle:compiler:$lifecycleVersion")
        testImplementation("android.arch.core:core-testing:$lifecycleVersion")
        // Android UI
        compile("com.android.support:appcompat-v7:28.0.0")
        implementation("com.android.support.constraint:constraint-layout:1.1.3")
        implementation("com.android.support:support-v4:28.0.0")
        implementation("androidx.cardview:cardview:1.0.0")
        implementation("com.android.support:recyclerview-v7:28.0.0")
        // Testing
        testImplementation("junit:junit:4.12")
        androidTestImplementation("com.android.support.test:runner:1.0.2")
        androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
        implementation("com.android.support:design:28.0.0")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.31")
}
repositories {
        mavenCentral()
}

