plugins { `kotlinx-serialization` }
android {
	buildTypes.onEach {
		it.buildConfigField("String", "QiitaClientId", QiitaClientId)
		it.buildConfigField("String", "QiitaClientSecret", QiitaClientSecret)
	}
	libraryVariants.all {
		generateBuildConfigProvider.configure {
			enabled = true
		}
	}
}
kotlinCompile {
	kotlinOptions.freeCompilerArgs += KOTLIN_USE_OPT
}
dependencies {
	api(Libs.`ktor-client-okhttp`)
	implementation(Libs.`ktor-client-android`)
	implementation(Libs.`ktor-client-serialization-jvm`)
	api(Libs.`koin-core`)
	api(Libs.`kotlinx-serialization-runtime`)
	implementation(Libs.`koin-android`)
	implementation(Libs.`security-crypto`)
}
