android {
	buildTypes.onEach {
		it.buildConfigField("String", "QiitaClientId", QiitaClientId)
		it.buildConfigField("String", "QiitaClientSecret", QiitaClientSecret)
	}
//	libraryVariants.all {
//		generateBuildConfigProvider.configure {
//			enabled = true
//		}
//	}
}
dependencies {
	implementation(project(Modules.data))
	implementation(project(Modules.`domains-auth`))
	implementation(Libs.`kotlinx-serialization-runtime`)
	implementation(Libs.`preference-ktx`)
	implementation(Libs.`security-crypto`)
	implementation(Libs.`koin-android`)
}
