plugins {
	id("kotlin-android")
	id("kotlin-android-extensions")
	`kotlinx-serialization`
	`navigation-safeargs-kotlin`
}
android {
	defaultConfig {
		applicationId = "com.sorrowblue.qiichan"
		versionCode = 1
		versionName = "1.0.0"
	}
	signingConfigs {
		release {
			storeFile = file("M:/sorro/Documents/keystore.jks")
			storePassword = "storeyuukiasuna2s2"
			keyAlias = "SorrowBlueApp"
			keyPassword = "appyuukiasuna2s2"
		}

	}
	buildTypes {
		debug {
			applicationIdSuffix = ".debug"
			versionNameSuffix = "_debug"
		}
		release {
			isMinifyEnabled = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
			signingConfig = signingConfigs.getByName(name)
		}
	}
	buildFeatures {
		dataBinding = true
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

dependencies {
	implementation(Libs.appcompat)
	implementation(Libs.`legacy-support-v4`)
	implementation(Libs.`koin-android`)
	implementation(project(Modules.ui))
	implementation(project(Modules.`ui-user`))
	implementation(project(Modules.`domains-user`))
	implementation(project(Modules.`ui-trend`))
	implementation(project(Modules.`ui-auth`))
	implementation(project(Modules.`ui-tagfeed`))
	implementation(project(Modules.`ui-settings`))
	implementation(project(Modules.module))
}
