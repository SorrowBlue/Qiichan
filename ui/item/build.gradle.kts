plugins {
	`kotlin-kapt`
	`navigation-safeargs-kotlin`
}
android {
	resourcePrefix("item_")
	buildFeatures {
		dataBinding = true
	}
}

dependencies {
	implementation(project(Modules.ui))
	api(project(Modules.`domains-item`))
	api(project(Modules.`domains-auth`))
	implementation("io.noties.markwon:core:4.3.1")
	implementation("io.noties.markwon:image-coil:4.3.1")
	implementation("io.noties.markwon:html:4.3.1")
	implementation("io.noties.markwon:linkify:4.3.1")
	implementation("io.noties.markwon:syntax-highlight:4.3.1")
	implementation("io.noties:prism4j:2.0.0")
	implementation("io.noties.markwon:editor:4.3.1")
	kapt("io.noties:prism4j-bundler:2.0.0")
}
configurations.all {
	exclude("org.jetbrains", "annotations-java5")
}
