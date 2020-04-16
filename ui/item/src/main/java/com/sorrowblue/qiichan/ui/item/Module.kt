package com.sorrowblue.qiichan.ui.item

import com.sorrowblue.qiichan.domains.item.QiitaItem
import com.sorrowblue.qiichan.ui.item.comment.CommentsAdapter
import com.sorrowblue.qiichan.ui.item.comment.CommentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiItemModule = module {
	factory { ItemTagAdapter() }
	factory { CommentsAdapter() }
	viewModel { ItemViewModel(adapter = get(), repo = get()) }
	viewModel { (item: QiitaItem) -> CommentsViewModel(item = item, adapter = get(), repo = get(), userService = get()) }
}
