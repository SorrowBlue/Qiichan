plugins { `kotlinx-serialization` }

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
