plugins {
	`kotlinx-serialization`
	`kotlin-android-extensions`
}

kotlinCompile {
	kotlinOptions.freeCompilerArgs += KOTLIN_INLINE_CLASSES
	kotlinOptions.freeCompilerArgs += KOTLIN_USE_OPT
}

dependencies {
	api(project(Modules.`domains-user`))
	implementation(Libs.`lifecycle-livedata-ktx`)
}
