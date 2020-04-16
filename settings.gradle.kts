rootProject.name = "Qiichan"
include(":app", ":module", ":ui", ":domains", ":data")
val excludeFolder = setOf("build", "src", "schemas", "libs")
listOf("ui", "domains", "data").forEach { folder ->
	file("/$folder").listFiles()
		?.filter { it.isDirectory && !excludeFolder.contains(it.name) }?.forEach { library ->
			include(":$folder:${library.name}")
			library.listFiles()
				?.filter { it.isDirectory && !excludeFolder.contains(it.name) }
				?.forEach { subLibrary ->
					include(":$folder:${library.name}:${subLibrary.name}")
				}
		}
}
