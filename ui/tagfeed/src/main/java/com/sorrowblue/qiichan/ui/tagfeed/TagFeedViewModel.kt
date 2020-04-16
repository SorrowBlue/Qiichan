package com.sorrowblue.qiichan.ui.tagfeed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorrowblue.qiichan.domains.auth.QiitaAuthService
import com.sorrowblue.qiichan.domains.item.QiitaItemRepository
import com.sorrowblue.qiichan.domains.item.QiitaSimpleItem
import com.sorrowblue.qiichan.domains.tag.QiitaTagRepository
import com.sorrowblue.qiichan.ui.lifecycle.NonNullLiveData
import kotlinx.coroutines.launch

internal class TagFeedViewModel(
	val adapter: TagFeedAdapter,
	private val tag: QiitaTagRepository,
	private val service: QiitaAuthService,
	private val repo: QiitaItemRepository
) : ViewModel() {

	val isRefreshing = NonNullLiveData(false)

	init {
		refresh()
	}

	fun refresh() {
		isRefreshing.value = true
		viewModelScope.launch {
			kotlin.runCatching {
				service.authUser?.userId?.let { tag.followingTags(it) }
			}.onFailure {
				Log.e("TagFeedViewModel", "authenticatedUser() ${it.message}")
			}.getOrNull()?.map { repo.tagFeeds(it.id) }?.flatten()
				?.sortedByDescending(QiitaSimpleItem::updateAt)?.let {
				adapter.currentList = it
			}
			isRefreshing.postValue(false)
		}
	}
}
