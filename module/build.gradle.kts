dependencies {
	implementation(project(Modules.ui))
	implementation(project(Modules.`ui-auth`))
	implementation(project(Modules.`ui-user`))
	implementation(project(Modules.`ui-item`))
	implementation(project(Modules.`ui-trend`))
	implementation(project(Modules.`ui-tagfeed`))
	implementation(project(Modules.`ui-tag`))
	implementation(project(Modules.data))
	implementation(project(Modules.`data-auth`))
	implementation(project(Modules.`data-user`))
	implementation(project(Modules.`data-item`))
	implementation(project(Modules.`data-tag`))
	implementation(Libs.`koin-core`)
}