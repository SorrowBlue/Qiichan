plugins {
	`kotlin-kapt`
	`navigation-safeargs-kotlin`
}

android {
	resourcePrefix("user_")
	buildFeatures {
		dataBinding = true
	}
}

dependencies {
	implementation(project(Modules.ui))
	implementation(project(Modules.`domains-user`))
	implementation(project(Modules.`domains-tag`))
	implementation(project(Modules.`domains-item`))
	implementation(project(Modules.`ui-tag`))
	implementation(project(Modules.`ui-item`))
	implementation(Libs.browser)
}
