plugins {
	`kotlin-kapt`
	`navigation-safeargs-kotlin`
}

android {
	resourcePrefix("trend_")
	buildFeatures {
		dataBinding = true
	}
}

dependencies {
	implementation(project(Modules.ui))
	implementation(project(Modules.`ui-user`))
	implementation(project(Modules.`ui-item`))
	implementation(project(Modules.`domains-item`))
}
