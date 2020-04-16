package com.sorrowblue.qiichan.ui.tagfeed

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiTagFeedModel
	get() = module {
		factory { TagFeedAdapter() }
		viewModel { TagFeedViewModel(adapter = get(), tag = get(), service = get(), repo = get()) }
	}
