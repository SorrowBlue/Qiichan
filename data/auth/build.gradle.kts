import org.jetbrains.kotlin.konan.properties.Properties

android {
	buildTypes.onEach {
		val properties = Properties()
		properties.load(project.rootProject.file("local.properties").inputStream())
		val id = properties.getProperty("QiitaClientId")
		val secret = properties.getProperty("QiitaClientSecret")
		it.buildConfigField("String", "QiitaClientId", "\"${id}\"")
		it.buildConfigField("String", "QiitaClientSecret", "\"${secret}\"")
	}
}

kotlinCompile {
	kotlinOptions.freeCompilerArgs += KOTLIN_USE_OPT
}

dependencies {
	implementation(project(Modules.data))
	implementation(project(Modules.`domains-auth`))
	implementation(Libs.`kotlinx-serialization-runtime`)
	implementation(Libs.`preference-ktx`)
	implementation(Libs.`security-crypto`)
	implementation(Libs.`koin-android`)
}
