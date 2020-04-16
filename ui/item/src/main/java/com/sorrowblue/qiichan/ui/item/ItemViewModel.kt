package com.sorrowblue.qiichan.ui.item

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.sorrowblue.qiichan.domains.item.QiitaItem
import com.sorrowblue.qiichan.domains.item.QiitaItemId
import com.sorrowblue.qiichan.domains.item.QiitaItemRepository
import kotlinx.coroutines.launch

internal class ItemViewModel(val adapter: ItemTagAdapter, private val repo: QiitaItemRepository) :
	ViewModel() {

	val item: MutableLiveData<QiitaItem?> = MutableLiveData()

	init {
		item.map { it?.tags ?: emptyList() }.observeForever { adapter.currentList = it }
	}

	fun setItemId(itemId: QiitaItemId) {
		viewModelScope.launch {
			kotlin.runCatching { repo.item(itemId) }.onFailure { Log.e("Item", it.message.toString()) }.getOrNull().also(item::postValue)
		}
	}
}
