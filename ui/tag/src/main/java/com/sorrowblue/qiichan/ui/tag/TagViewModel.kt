package com.sorrowblue.qiichan.ui.tag

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorrowblue.qiichan.domains.tag.QiitaTag
import com.sorrowblue.qiichan.domains.tag.QiitaTagId
import com.sorrowblue.qiichan.domains.tag.QiitaTagRepository
import com.sorrowblue.qiichan.ui.lifecycle.SingleLiveEvent
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf

internal class TagViewModel(
	tag: QiitaTag?,
	tagId: QiitaTagId?,
	private val repo: QiitaTagRepository
) : ViewModel() {

	companion object {
		fun params(tag: QiitaTag?, itemTag: String?) = parametersOf(tag, itemTag)
	}

	val tag = MutableLiveData<QiitaTag>()
	val isFollowing: MutableLiveData<Boolean> = MutableLiveData()
	val action = SingleLiveEvent<TagAction>()

	init {
		if (tag != null) {
			this.tag.value = tag
		} else if (tagId != null) {
			viewModelScope.launch {
				kotlin.runCatching {
					repo.tag(tagId)
				}.onSuccess { this@TagViewModel.tag.postValue(it) }
			}
		} else {
			this.tag.value = null
		}
	}

	fun postAction(action: TagAction) {
		this.action.value = action
	}

	fun follow() {

	}

	fun unfollow() {

	}
}

enum class TagAction { ITEMS, TREND }
