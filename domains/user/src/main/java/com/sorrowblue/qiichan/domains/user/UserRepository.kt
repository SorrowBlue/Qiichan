package com.sorrowblue.qiichan.domains.user

import androidx.annotation.IntRange

interface UserRepository {

	suspend fun followees(
		id: UserId,
		@IntRange(from = 1, to = 100) page: Int = 1,
		@IntRange(from = 1, to = 100) perPage: Int = 20
	): List<QiitaUser>

	suspend fun followers(
		id: UserId,
		@IntRange(from = 1, to = 100) page: Int = 1,
		@IntRange(from = 1, to = 100) perPage: Int = 20
	): List<QiitaUser>

	suspend fun user(id: UserId): QiitaUser
	suspend fun isFollowing(id: UserId): Boolean
	suspend fun follow(id: UserId): Boolean
	suspend fun unfollow(id: UserId): Boolean
}
