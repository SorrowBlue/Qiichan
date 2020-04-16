package com.sorrowblue.qiichan.domains.auth

import android.net.Uri

interface QiitaAuthService {
	suspend fun requestAccessTokens(code: String, state: String):Boolean
	val authUser: QiitaAuthUser?
	fun authorizeUri(scope: QiitaScope): Uri
	suspend fun authenticatedUser(): QiitaAuthUser
	suspend fun logout()
}
