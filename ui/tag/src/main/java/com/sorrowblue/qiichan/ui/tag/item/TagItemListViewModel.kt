package com.sorrowblue.qiichan.ui.tag.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorrowblue.qiichan.domains.item.QiitaItemRepository
import com.sorrowblue.qiichan.domains.tag.QiitaTag
import com.sorrowblue.qiichan.domains.tag.QiitaTagId
import com.sorrowblue.qiichan.ui.lifecycle.NonNullLiveData
import kotlinx.coroutines.launch

internal class TagItemListViewModel(
	val adapter: TagItemListAdapter,
	private val repo: QiitaItemRepository
) : ViewModel() {
	val isLoading = NonNullLiveData(false)

	fun setTagId(tagId: QiitaTagId) {
		isLoading.value = true
		viewModelScope.launch {
			kotlin.runCatching {
				repo.items(tagId, 1, 20)
			}.onSuccess {
				adapter.currentList = it
			}
			isLoading.postValue(false)
		}
	}
}
