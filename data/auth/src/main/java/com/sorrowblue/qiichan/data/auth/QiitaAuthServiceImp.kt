package com.sorrowblue.qiichan.data.auth

import android.net.Uri
import android.util.Log
import androidx.core.content.edit
import androidx.core.net.toUri
import com.sorrowblue.qiichan.data.AccessToken
import com.sorrowblue.qiichan.data.AndroidClient
import com.sorrowblue.qiichan.data.QIITA_API_ROOT
import com.sorrowblue.qiichan.domains.auth.QiitaAuthService
import com.sorrowblue.qiichan.domains.auth.QiitaAuthUser
import com.sorrowblue.qiichan.domains.auth.QiitaScope
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.UnstableDefault
import kotlin.random.Random

private const val QIITA_SCOPE = "read_qiita write_qiita"
private const val QIITA_TEAM_SCOPE = "read_qiita_team write_qiita_team"

internal class QiitaAuthServiceImp(
	private val client: AndroidClient,
	private val tokenService: AccessTokenService,
	private val secureService: SecureService
) : QiitaAuthService {

	private var state: String = ""
	private var _authUser: QiitaAuthUser? = null

	init {
		client.token = tokenService.token?.token
	}

	@OptIn(UnstableDefault::class)
	override suspend fun requestAccessTokens(code: String, state: String): Boolean {
		if (secureService.preferences.getString("state", null) != state) {
			return false
		}
		tokenService.token?.let { client.delete<Unit>("/access_tokens/${it.token}") }
		tokenService.delete()
		client.token = null
		Log.d("APPAPP", "client.post before")
		client.post<AccessToken>("/access_tokens") {
			contentType(ContentType.parse("application/json"))
			parameter("client_id", BuildConfig.QiitaClientId)
			parameter("client_secret", BuildConfig.QiitaClientSecret)
			parameter("code", code)
		}.also {
			Log.d("APPAPP", "client.post after")
			client.token = it.token
//			Log.d(javaClass.simpleName, Json.stringify(AccessToken.serializer(), it))
			tokenService.set(it)
		}
		return true
	}

	override suspend fun authenticatedUser(): QiitaAuthUser =
		client.get<QiitaAuthUser>("/authenticated_user").also { _authUser = it }

	override val authUser: QiitaAuthUser? get() = _authUser

	override suspend fun logout() {
		tokenService.token?.let { client.delete<Unit>("/access_tokens/${it.token}") }
		tokenService.delete()
		client.token = null
		_authUser = null
	}

	override fun authorizeUri(scope: QiitaScope): Uri {
		val mScope = when (scope) {
			QiitaScope.QIITA -> QIITA_SCOPE
			QiitaScope.QIITA_TEAM -> QIITA_TEAM_SCOPE
		}
		state = generateState()
		secureService.preferences.edit { putString("state", state) }
		return "$QIITA_API_ROOT/oauth/authorize?client_id=${BuildConfig.QiitaClientId}&scope=$mScope&state=$state".toUri()
	}

	private fun generateState(): String {
		val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
		return 1.rangeTo(40).map { Random.nextInt(0, charPool.size) }.map(charPool::get)
			.joinToString("")
	}
}

