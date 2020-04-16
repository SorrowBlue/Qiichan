package com.sorrowblue.qiichan

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
	@Test
	fun addition_isCorrect() {
		val json = """
			{"client_id":"cc43a7b4ea4db44f4d3d5ab6622bbacbf300f8f2","token":"4dd3de050683b333ae7c6bfbed444a3309aa4646","scopes":["read_qiita"]}
		""".trimIndent()
		print(
			Json(JsonConfiguration.Stable.copy(isLenient = true)).stringify(
				AccessToken.serializer(),
				AccessToken(
					"cc43a7b4ea4db44f4d3d5ab6622bbacbf300f8f2",
					"4dd3de050683b333ae7c6bfbed444a3309aa4646",
					scopes = listOf(AccessToken.Scope.read_qiita)
				)
			)
		)
		print(
			Json(JsonConfiguration.Stable.copy(isLenient = true)).parse(
				AccessToken.serializer(),
				json
			)
		)
		assertEquals(4, 2 + 2)
	}
}
