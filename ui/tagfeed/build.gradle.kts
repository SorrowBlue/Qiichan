plugins {
	`kotlin-kapt`
	`navigation-safeargs-kotlin`
}

android {
	resourcePrefix("tagfeed_")
	buildFeatures {
		dataBinding = true
	}
}

dependencies {
	implementation(project(Modules.ui))
	implementation(project(Modules.`ui-item`))
	implementation(project(Modules.`ui-tag`))
	implementation(project(Modules.`domains-item`))
	implementation(project(Modules.`domains-tag`))
	implementation(Libs.swiperefreshlayout)
}
