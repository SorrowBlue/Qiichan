plugins {
	`kotlin-kapt`
	`kotlinx-serialization`
	`kotlin-android-extensions`
}

kotlinCompile {
	kotlinOptions.freeCompilerArgs += KOTLIN_INLINE_CLASSES
}

dependencies {
	api(project(Modules.`domains-tag`))
	implementation(Libs.`kotlinx-coroutines-android`)
}
