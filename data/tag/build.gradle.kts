plugins {
	`kotlinx-serialization`
}

dependencies {
	implementation(project(Modules.data))
	implementation(project(Modules.`domains-user`))
	implementation(project(Modules.`domains-tag`))
}
