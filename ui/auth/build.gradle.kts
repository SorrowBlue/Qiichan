plugins {
	`kotlin-kapt`
	`navigation-safeargs-kotlin`
}
android {
	resourcePrefix("auth_")
	buildFeatures {
		dataBinding = true
	}
}

dependencies {
	implementation(project(Modules.ui))
	implementation(Libs.browser)
	implementation(project(Modules.`ui-tag`))
	implementation(project(Modules.`ui-item`))
	implementation(project(Modules.`ui-user`))
	api(project(Modules.`domains-auth`))
}
