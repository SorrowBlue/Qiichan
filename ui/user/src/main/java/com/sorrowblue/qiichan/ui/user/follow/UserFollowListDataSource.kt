package com.sorrowblue.qiichan.ui.user.follow

import com.sorrowblue.qiichan.domains.user.QiitaUser
import com.sorrowblue.qiichan.domains.user.UserRepository
import com.sorrowblue.qiichan.ui.component.CoroutinesPositionalDataSource
import kotlinx.coroutines.CoroutineScope

class UserFollowListDataSource(
	scope: CoroutineScope,
	private val mode: FollowListMode,
	private val user: QiitaUser,
	private val repo: UserRepository
) : CoroutinesPositionalDataSource<QiitaUser>(scope) {

	class Factory(
		scope: CoroutineScope,
		private val mode: FollowListMode,
		private val user: QiitaUser,
		private val repo: UserRepository
	) : CoroutinesPositionalDataSource.Factory<QiitaUser, UserFollowListDataSource>(scope) {
		override fun createDataSource() = UserFollowListDataSource(scope, mode, user, repo)
	}

	override val totalCount = when (mode) {
		FollowListMode.FOLLOWERS -> user.followersCount
		FollowListMode.FOLLOWEES -> user.followeesCount
	}

	override suspend fun loadInitial(startPosition: Int, loadSize: Int, pageSize: Int) =
		when (mode) {
			FollowListMode.FOLLOWERS -> repo.followers(user.userId, 1, loadSize)
			FollowListMode.FOLLOWEES -> repo.followees(user.userId, 1, loadSize)
		}

	override suspend fun loadRange(startPosition: Int, loadSize: Int) =
		when (mode) {
			FollowListMode.FOLLOWERS ->
				repo.followers(user.userId, startPosition / loadSize, loadSize)
			FollowListMode.FOLLOWEES ->
				repo.followees(user.userId, startPosition / loadSize, loadSize)
		}
}
