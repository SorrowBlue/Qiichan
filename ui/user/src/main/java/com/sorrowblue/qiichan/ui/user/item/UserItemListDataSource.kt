package com.sorrowblue.qiichan.ui.user.item

import com.sorrowblue.qiichan.domains.item.QiitaItem
import com.sorrowblue.qiichan.domains.item.QiitaItemRepository
import com.sorrowblue.qiichan.domains.user.QiitaUser
import com.sorrowblue.qiichan.ui.component.CoroutinesPositionalDataSource
import kotlinx.coroutines.CoroutineScope

internal class UserItemListDataSource(
	scope: CoroutineScope,
	user: QiitaUser,
	private val repo: QiitaItemRepository
) : CoroutinesPositionalDataSource<QiitaItem>(scope) {

	class Factory(
		scope: CoroutineScope,
		private val user: QiitaUser,
		private val repo: QiitaItemRepository
	) : CoroutinesPositionalDataSource.Factory<QiitaItem, UserItemListDataSource>(scope) {
		override fun createDataSource() = UserItemListDataSource(scope, user, repo)
	}

	private val userId = user.userId

	override val totalCount = user.itemsCount

	override suspend fun loadRange(startPosition: Int, loadSize: Int) =
		repo.userItems(userId, startPosition / loadSize, loadSize)

	override suspend fun loadInitial(startPosition: Int, loadSize: Int, pageSize: Int) =
		repo.userItems(userId, 1, loadSize)

}
