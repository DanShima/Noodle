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
        packagingOptions {
        exclude("META-INF/atomicfu.kotlin_module")
        }
}

dependencies {
        val roomVersion = "2.1.0-alpha07"
        val lifecycleVersion = "2.0.0"
        val daggerVersion = "2.18"
        val supportLibVersion = "1.0.0"
        val coroutineVersion = "1.2.0"

        implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.31")
        implementation("androidx.appcompat:appcompat:1.1.0-alpha04")
        implementation("androidx.core:core-ktx:1.1.0-alpha05")
        // Room
        implementation("androidx.room:room-runtime:$roomVersion")
        kapt("androidx.room:room-compiler:$roomVersion")
        implementation("androidx.room:room-rxjava2:$roomVersion")
        implementation("androidx.room:room-ktx:$roomVersion")
        testImplementation("androidx.room:room-testing:$roomVersion")
        // Lifecycle
        kapt("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
        // Android UI
        implementation("androidx.constraintlayout:constraintlayout:1.1.3")
        implementation("androidx.cardview:cardview:$supportLibVersion")
        implementation("androidx.recyclerview:recyclerview:$supportLibVersion")
        implementation("com.google.android.material:material:$supportLibVersion")
        // Testing
        testImplementation("junit:junit:4.12")
        androidTestImplementation("androidx.test:runner:1.2.0-alpha05")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0-alpha05")
        testImplementation("androidx.arch.core:core-testing:$lifecycleVersion")
        // Kotlin Coroutines
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
        api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")
        // Retrofit
        implementation("com.squareup.retrofit2:retrofit:2.5.0")
        implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-experimental-adapter:1.0.0")
}
repositories {
        mavenCentral()
}

