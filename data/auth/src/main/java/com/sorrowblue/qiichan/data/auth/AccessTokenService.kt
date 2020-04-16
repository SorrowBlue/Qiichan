package com.sorrowblue.qiichan.data.auth

import androidx.core.content.edit
import com.sorrowblue.qiichan.data.AccessToken
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

internal class AccessTokenService(private val service: SecureService) {

	companion object {
		private const val PREFERENCE_KEY = "token"
	}

	@OptIn(UnstableDefault::class)
	fun set(accessToken: AccessToken) = service.preferences.edit(true) {
		putString(PREFERENCE_KEY, Json.stringify(AccessToken.serializer(), accessToken))
	}

	fun delete() = service.preferences.edit { remove(PREFERENCE_KEY) }

	@OptIn(UnstableDefault::class)
	val token: AccessToken?
		get() = service.preferences.getString(PREFERENCE_KEY, null)
			?.let { Json.parse(AccessToken.serializer(), it) }
}
