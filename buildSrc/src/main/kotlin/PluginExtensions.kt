import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginAware
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.`android-application`: PluginDependencySpec get() = id("com.android.application")
val PluginDependenciesSpec.`android-library`: PluginDependencySpec get() = id("com.android.library")
val PluginDependenciesSpec.`kotlinx-serialization`: PluginDependencySpec get() = id("kotlinx-serialization")
val PluginDependenciesSpec.`navigation-safeargs-kotlin` get() = id("androidx.navigation.safeargs.kotlin")


val PluginDependenciesSpec.`ben-manes-versions`: PluginDependencySpec get() = id("com.github.ben-manes.versions") /*version "0.28.0"*/
val PluginAware.`android-library` get() = apply(plugin = "com.android.library")
val PluginAware.`android-application` get() = apply(plugin = "com.android.application")
val PluginAware.`oss-licenses-plugin` get() = apply(plugin = "com.google.android.gms.oss-licenses-plugin")
val PluginAware.`kotlinx-serialization` get() = apply(plugin = "kotlinx-serialization")
val PluginAware.`navigation-safeargs-kotlin` get() = apply(plugin = "androidx.navigation.safeargs.kotlin")
val PluginAware.`kotlin-android-extensions` get() = apply(plugin = "kotlin-android-extensions")
val PluginAware.`kotlin-android` get() = apply(plugin = "kotlin-android")
val PluginAware.`kotlin-kapt` get() = apply(plugin = "kotlin-kapt")

fun DependencyHandler.`gradle-versions-plugin`(version: String): String =
	"com.github.ben-manes:gradle-versions-plugin:$version"

fun DependencyHandler.`android-tools-build-gradle`(version: String): String =
	"com.android.tools.build:gradle:$version"

fun DependencyHandler.`kotlin-gradle-plugin`(version: String): String =
	"org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

fun DependencyHandler.`google-services`(version: String): String =
	"com.google.gms:google-services:$version"

fun DependencyHandler.`navigation-safe-args-gradle-plugin`(version: String): String =
	"androidx.navigation:navigation-safe-args-gradle-plugin:$version"

fun DependencyHandler.`kotlinx-serialization`(version: String): String =
	"org.jetbrains.kotlin:kotlin-serialization:$version"

fun DependencyHandler.`oss-licenses-plugin`(version: String) =
	"com.google.android.gms:oss-licenses-plugin:$version"
