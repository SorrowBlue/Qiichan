plugins {
	`kotlin-kapt`
	`navigation-safeargs-kotlin`
}

android {
	resourcePrefix("tag_")
	buildFeatures {
		dataBinding = true
	}
}

dependencies {
	implementation(project(Modules.ui))
	implementation(project(Modules.`domains-tag`))
	implementation(project(Modules.`domains-auth`))
	implementation(project(Modules.`ui-item`))
}
