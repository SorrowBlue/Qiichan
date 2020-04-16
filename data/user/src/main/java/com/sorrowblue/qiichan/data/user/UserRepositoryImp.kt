package com.sorrowblue.qiichan.data.user

import android.util.Log
import com.sorrowblue.qiichan.data.AndroidClient
import com.sorrowblue.qiichan.domains.user.QiitaUser
import com.sorrowblue.qiichan.domains.user.UserId
import com.sorrowblue.qiichan.domains.user.UserRepository

internal class UserRepositoryImp(private val client: AndroidClient) : UserRepository {
	override suspend fun followees(id: UserId, page: Int, perPage: Int): List<QiitaUser> =
		client.get("/users/${id.value}/followees?page=$page&per_page=$perPage")

	override suspend fun followers(id: UserId, page: Int, perPage: Int): List<QiitaUser> =
		client.get("/users/${id.value}/followers?page=$page&per_page=$perPage")

	override suspend fun user(id: UserId): QiitaUser =
		client.get("/users/${id.value}")

	override suspend fun isFollowing(id: UserId): Boolean {
		return kotlin.runCatching { client.get<Unit>("/users/${id.value}/following") }
			.onSuccess {
				Log.d(javaClass.simpleName, "following onSuccess")
			}.onFailure {
				Log.d(javaClass.simpleName, "following onFailure ${it.message}")
			}
			.getOrNull()?.let { true } ?: false
	}

	override suspend fun follow(id: UserId): Boolean {
		return kotlin.runCatching { client.put<Unit>("/users/${id.value}/following") }
			.onSuccess {
				Log.d(javaClass.simpleName, "follow onSuccess")
			}.onFailure {
				Log.d(javaClass.simpleName, "follow onFailure")
			}
			.getOrNull()?.let { true } ?: false
	}

	override suspend fun unfollow(id: UserId): Boolean {
		return kotlin.runCatching { client.delete<Unit>("/users/${id.value}/following") }
			.onSuccess {
				Log.d(javaClass.simpleName, "follow onSuccess")
			}.onFailure {
				Log.d(javaClass.simpleName, "follow onFailure")
			}
			.getOrNull()?.let { true } ?: false
	}
}
