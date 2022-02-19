import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI

object BuildConfig {

    const val compileSdkVersion = 32
    const val buildToolsVersion = "30.0.2"
    const val minSdkVersion = 21
    const val targetSdkVersion = 32

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0
    private const val versionBuild = 2

    const val versionName =
        "$versionMajor.$versionMinor.$versionPatch"
    const val versionCode =
        versionMajor * 1000000 + versionMinor * 10000 + versionPatch * 100 + versionBuild

}


object CommonLibs {
    private const val kotlin = "1.4.30"
    private const val kotlin_gradle_ver = "1.6.0"
    private const val gradlePluginVer = "7.0.0"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlin_gradle_ver}"
    const val gradlePlugin = "com.android.tools.build:gradle:$gradlePluginVer"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${kotlin}"
    const val timber = "com.jakewharton.timber:timber:4.7.1"

}

object SplashScreen{
    const val splash_core = "androidx.core:core-splashscreen:1.0.0-beta01"
}

object Material {
    const val material = "com.google.android.material:material:1.4.0"
}

object DataStore {
    private const val version = "1.0.0"

    const val android = "androidx.datastore:datastore-preferences:$version"
}

object OkHttp {
    private const val version = "4.9.0"

    const val client = "com.squareup.okhttp3:okhttp:$version"
    const val logger = "com.squareup.okhttp3:logging-interceptor:$version"
}

object Gson {
    private const val version = "2.8.6"
    const val core = "com.google.code.gson:gson:$version"

}

object Retrofit {
    private const val version = "2.9.0"

    const val core = "com.squareup.retrofit2:retrofit:$version"
    const val gson_converter = "com.squareup.retrofit2:converter-gson:$version"
}

object Coroutines {
    private const val coroutines = "1.6.0"

    const val coroutine_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines"
    const val coroutine_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines"
    const val coroutine_rx = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:1.3.6"
}


object AndroidArchLifeCycle {
    private const val lifecycle_version = "2.3.1"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    const val compiler = "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"
    const val runtime =  "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-rc01"



}

object AndroidArchNavigation {
    private const val nav_version = "2.3.5"
    const val fragment = "androidx.navigation:navigation-fragment-ktx:$nav_version"
    const val ui = "androidx.navigation:navigation-ui-ktx:$nav_version"
    const val gradle_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
}

object AndroidArchRoom {
    private const val room_version = "2.4.1"
    const val runtime = "androidx.room:room-runtime:$room_version"
    const val compiler = "androidx.room:room-compiler:$room_version"
    const val ktx = "androidx.room:room-ktx:$room_version"
}

object AndroidX {
    const val core = "androidx.core:core-ktx:1.7.0"
    const val appcompat = "androidx.appcompat:appcompat:1.4.0"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:2.1.3"
    const val recycler_view = "androidx.recyclerview:recyclerview:1.1.0"
}

object Koin {
    private const val version = "2.2.2"
    const val android = "org.koin:koin-android:$version"
    const val scope = "org.koin:koin-androidx-scope:$version"
    const val viewmodel = "org.koin:koin-androidx-viewmodel:$version"
    const val fragment = "org.koin:koin-androidx-fragment:$version"
    const val extension = "org.koin:koin-androidx-ext:$version"

}

object Jsoup {
    const val jsoup = "org.jsoup:jsoup:1.13.1"
}

object Epoxy {
    const val jsoup = "org.jsoup:jsoup:1.13.1"
}


object Repo {
    @JvmStatic
    fun addRepos(handler: RepositoryHandler) {
        handler.google()
        handler.jcenter()
        handler.maven { url = URI.create("https://jitpack.io") }
    }
}