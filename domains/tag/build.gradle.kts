plugins {
	`kotlinx-serialization`
	`kotlin-android-extensions`
}

kotlinCompile {
	kotlinOptions.freeCompilerArgs += KOTLIN_INLINE_CLASSES
}

dependencies {
	api(project(Modules.`domains-user`))
	implementation(Libs.`kotlinx-serialization-runtime`)
}
