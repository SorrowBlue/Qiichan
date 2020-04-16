plugins {
	`kotlin-kapt`
}

android {
	buildFeatures {
		dataBinding = true
	}
}

dependencies {
	api(Libs.`klock-jvm`)
	api(Libs.material)
	api(Libs.constraintlayout)
	api(Libs.`paging-runtime-ktx`)
	api("com.google.android:flexbox:2.0.1")
	api(Libs.`navigation-ui-ktx`)
	api(Libs.`navigation-fragment-ktx`)
	api(Libs.`lifecycle-livedata-ktx`)
	api(Libs.`lifecycle-viewmodel-ktx`)
	api(Libs.`koin-androidx-viewmodel`)
	api(Libs.transition)
	implementation(Libs.browser)
	api(Libs.recyclerview)
	api(Libs.`koin-android`)
	api(Libs.`activity-ktx`)
	api(Libs.`fragment-ktx`)
	api(Libs.coil)
	api(Libs.`kotlinx-coroutines-android`)
	api("com.facebook.shimmer:shimmer:0.5.0")
	implementation(Libs.`lifecycle-process`)
	implementation(Libs.swiperefreshlayout)
}
