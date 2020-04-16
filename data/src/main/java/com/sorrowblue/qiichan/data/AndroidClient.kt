package com.sorrowblue.qiichan.data

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

private const val PREFERENCE_KEY = "auth_key"
const val QIITA_API_ROOT = "https://qiita.com/api/v2"

class AndroidClient(private val context: Context) {

	var token: String? = null
	val client
		get() = HttpClient(Android) {
			install(JsonFeature) {
				serializer =
					KotlinxSerializer(
						Json(
							JsonConfiguration.Stable.copy(ignoreUnknownKeys = true)
						)
					)
			}
		}

	val opHttpClient get() = HttpClient(OkHttp)

	suspend inline fun getPlane(
		path: String,
		block: HttpRequestBuilder.() -> Unit = {}
	): String = opHttpClient.use { it.get(path, block) }

	suspend inline fun <reified T> get(path: String): T =
		client.use {
			it.get("$QIITA_API_ROOT$path") {
				token?.let {
					headers { append("Authorization", "Bearer $it") }
				}
			}
		}

	suspend inline fun <reified T> delete(path: String): T =
		client.use {
			it.delete("$QIITA_API_ROOT$path") {
				token?.let {
					headers { append("Authorization", "Bearer $it") }
				}
			}
		}

	suspend inline fun <reified T> post(
		path: String,
		block: HttpRequestBuilder.() -> Unit = {}
	): T =
		client.use {
			it.post("$QIITA_API_ROOT$path") {
				block()
				token?.let {
					headers { append("Authorization", "Bearer $it") }
				}
			}
		}

	suspend inline fun <reified T> patch(
		path: String,
		block: HttpRequestBuilder.() -> Unit = {}
	): T =
		client.use {
			it.patch("$QIITA_API_ROOT$path") {
				block()
				token?.let {
					headers { append("Authorization", "Bearer $it") }
				}
			}
		}

	suspend inline fun <reified T> put(path: String, block: HttpRequestBuilder.() -> Unit = {}): T =
		client.use {
			it.put("$QIITA_API_ROOT$path") {
				block()
				token?.let {
					headers { append("Authorization", "Bearer $it") }
				}
			}
		}
}
