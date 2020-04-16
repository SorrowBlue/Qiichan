// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
	repositories {
		google()
		jcenter()
	}
	dependencies {
		classpath(`android-tools-build-gradle`("4.0.0-beta04"))
		classpath(`kotlin-gradle-plugin`(Versions.kotlin))
		classpath(`oss-licenses-plugin`("0.10.2"))
		classpath(`navigation-safe-args-gradle-plugin`(Versions.navigation))
		classpath(`kotlinx-serialization`(Versions.kotlin))
		classpath(`gradle-versions-plugin`("0.28.0"))

		// NOTE: Do not place your application dependencies here; they belong
		// in the individual module build.gradle files
	}
}

allprojects {
	repositories {
		google()
		jcenter()
	}
}

subprojects {
	excludeRootFolder({
		if (project.name == "app") `android-application` else `android-library`
		`kotlin-android`
//		`kotlin-android-extensions`
//		`kotlin-kapt`
//		`navigation-safeargs-kotlin`
		dependencies {
			implementation(Libs.`kotlin-stdlib-jdk7`)
			implementation(Libs.`core-ktx`)
			testImplementation(Libs.junit)
			androidTestImplementation(Libs.`androidx-junit`)
			androidTestImplementation(Libs.`androidx-junit-ktx`)
			androidTestImplementation(Libs.`espresso-core`)
		}
	}){
		project {
			buildToolsVersion = "29.0.3"
			compileSdkVersion(29)
			defaultConfig {
				minSdkVersion(23)
				targetSdkVersion(29)
				testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
			}
			compileOptions {
				sourceCompatibility = JavaVersion.VERSION_1_8
				targetCompatibility = JavaVersion.VERSION_1_8
			}
			kotlinOptions {
				jvmTarget = "1.8"
			}
			packagingOptions {
				exclude("META-INF/**.kotlin_module")
			}
		}
		library {
			defaultConfig {
				consumerProguardFiles("consumer-rules.pro")
			}
			val enabledBuildConfigModule = setOf("data" to "auth")
			libraryVariants.all {
				generateBuildConfigProvider.configure {
					enabled =
						enabledBuildConfigModule.any { project.projectDir.parentFile.name == it.first && project.name == it.second }
				}
			}
		}
	}
}

apply(plugin = "com.github.ben-manes.versions")

tasks.register("clean", Delete::class) { delete(rootProject.buildDir) }
