import org.gradle.internal.impldep.jcifs.UniAddress
import org.gradle.internal.impldep.jcifs.UniAddress.getByName

plugins {
    id("com.android.application") version "8.0.0" // Use the latest version
    id("kotlin-android") version "1.9.23"
}

android {
    compileSdkVersion(33) // Or the latest version
    defaultConfig {
        var applicationId = "com.example.financial manager"
        minSdkVersion(21)
        targetSdkVersion(33)
        var versionCode = 1
        var versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            var isMinifyEnabled = false
        }
        // You can add other build types (e.g., debug) here if needed
        // getByName("debug") {
        //     // Debug-specific configuration
        // }
    }
    // Additional configurations can be added here if needed
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Room dependencies
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

fun getByName(s: String, function: () -> Unit): UniAddress? {
    TODO("Not yet implemented")
}

fun buildTypes(any: Any) {
    TODO("Not yet implemented")
}

fun android(function: () -> Unit) {
    TODO("Not yet implemented")
}

fun compileSdkVersion(i: Int) {
    TODO("Not yet implemented")
}

fun defaultConfig(function: () -> Unit) {

}

fun minSdkVersion(i: Int) {
    TODO("Not yet implemented")
}

fun targetSdkVersion(i: Int) {

}

fun kapt(s: String) {
    TODO("Not yet implemented")
}

fun androidTestImplementation(s: String) {

}

fun implementation(s: String) {
    TODO("Not yet implemented")
}

fun testImplementation(s: String) {
    TODO("Not yet implemented")
}
