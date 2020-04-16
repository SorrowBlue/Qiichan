plugins {
	`kotlinx-serialization`
}

kotlinCompile {
	kotlinOptions.freeCompilerArgs += KOTLIN_USE_OPT
}

dependencies {
	implementation(project(Modules.`domains-item`))
	implementation(project(Modules.`data-user`))
	implementation(project(Modules.data))
	implementation(Libs.`security-crypto`)
	implementation("org.jsoup:jsoup:1.13.1")
	implementation(Libs.`koin-android`)

}
