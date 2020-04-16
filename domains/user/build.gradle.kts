plugins {
	`kotlinx-serialization`
	`kotlin-android-extensions`
}

kotlinCompile {
	kotlinOptions.freeCompilerArgs += KOTLIN_INLINE_CLASSES
}

dependencies {
	api(project(Modules.domains))
	implementation(Libs.`lifecycle-livedata-ktx`)
	api(Libs.`kotlinx-serialization-runtime`)
}
