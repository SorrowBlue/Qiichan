package com.sorrowblue.qiichan.ui.user.item

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.sorrowblue.qiichan.domains.item.QiitaItem
import com.sorrowblue.qiichan.domains.item.QiitaItemRepository
import com.sorrowblue.qiichan.domains.user.QiitaUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.coroutines.CoroutineContext

internal class UserItemListDataSource(
	override val coroutineContext: CoroutineContext,
	private val user: QiitaUser
) :
	PositionalDataSource<QiitaItem>(), KoinComponent, CoroutineScope {

	class Factory(private val coroutineContext: CoroutineContext, private val user: QiitaUser) :
		DataSource.Factory<Int, QiitaItem>() {
		override fun create() = UserItemListDataSource(coroutineContext, user)

	}

	val isLoading: MutableLiveData<Boolean> = MutableLiveData()

	private val repo: QiitaItemRepository by inject()

	override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<QiitaItem>) {
		isLoading.postValue(true)
		launch {
			runCatching {
				repo.userItems(user.userId, params.startPosition / params.loadSize, params.loadSize)
			}.onSuccess(callback::onResult)
			isLoading.postValue(false)
		}
	}

	override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<QiitaItem>) {
		isLoading.postValue(true)
		launch {
			runCatching {
				repo.userItems(user.userId, 1, params.requestedLoadSize)
			}.onSuccess { callback.onResult(it, 0, user.itemsCount) }
			isLoading.postValue(false)
		}
	}
}
