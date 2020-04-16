package com.sorrowblue.qiichan.ui.trend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorrowblue.qiichan.domains.item.QiitaItemRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class TrendViewModel(val adapter: TrendItemAdapter, repo: QiitaItemRepository) : ViewModel() {
	val trendDateTime = repo.trendTime()

	init {
		viewModelScope.launch {
			delay(300)
			kotlin.runCatching { repo.trends() }.getOrNull()?.let { adapter.currentList = it }
		}
	}
}
