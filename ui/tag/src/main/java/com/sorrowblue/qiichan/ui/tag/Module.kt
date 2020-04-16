package com.sorrowblue.qiichan.ui.tag

import com.sorrowblue.qiichan.domains.tag.QiitaTag
import com.sorrowblue.qiichan.domains.tag.QiitaTagId
import com.sorrowblue.qiichan.domains.user.QiitaUser
import com.sorrowblue.qiichan.ui.tag.item.TagItemListAdapter
import com.sorrowblue.qiichan.ui.tag.item.TagItemListViewModel
import com.sorrowblue.qiichan.ui.tag.list.TagListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiTagModule
	get() = module {
		factory { TagAdapter() }
		viewModel { (user: QiitaUser) ->
			TagListViewModel(
				user,
				adapter = get(),
				repo = get()
			)
		}
		viewModel { (tag: QiitaTag?, tagId: String?) -> TagViewModel(tag, tagId?.let(::QiitaTagId), get()) }
		factory { TagItemListAdapter() }
		viewModel {  TagItemListViewModel(get(), get()) }
	}
