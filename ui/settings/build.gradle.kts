android {
	resourcePrefix("settings_")
}

dependencies {
	implementation(project(Modules.ui))
	implementation(project(Modules.`ui-auth`))
	implementation(Libs.`preference-ktx`)
}
