package com.sorrowblue.qiichan.ui.tag.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorrowblue.qiichan.domains.tag.QiitaTag
import com.sorrowblue.qiichan.domains.tag.QiitaTagRepository
import com.sorrowblue.qiichan.domains.user.QiitaUser
import com.sorrowblue.qiichan.ui.lifecycle.NonNullLiveData
import com.sorrowblue.qiichan.ui.tag.TagAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class TagListViewModel(
	private val user: QiitaUser?,
	val adapter: TagAdapter,
	private val repo: QiitaTagRepository
) : ViewModel() {
	val isLoading = NonNullLiveData(false)
	val isEmpty = NonNullLiveData(false)
	var sort = MutableLiveData(QiitaTag.Sort.COUNT)

	init {
		user?.let {
			viewModelScope.launch {
				delay(2000)
				adapter.currentList = repo.followingTags(it.userId).also {
					isEmpty.postValue(it.isEmpty())
				}
			}
		} ?: sort.observeForever {
			viewModelScope.launch {
				adapter.currentList = repo.tags(sort = it).also {
					isEmpty.postValue(it.isEmpty())
				}
			}
		}
	}
}
