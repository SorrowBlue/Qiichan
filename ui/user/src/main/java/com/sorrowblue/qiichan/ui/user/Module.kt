package com.sorrowblue.qiichan.ui.user

import com.sorrowblue.qiichan.domains.user.QiitaUser
import com.sorrowblue.qiichan.ui.user.follow.FollowListMode
import com.sorrowblue.qiichan.ui.user.follow.UserFollowListViewModel
import com.sorrowblue.qiichan.ui.user.follow.UserListAdapter
import com.sorrowblue.qiichan.ui.user.item.UserItemListAdapter
import com.sorrowblue.qiichan.ui.user.item.UserItemListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiUserModule
	get() = module {
		viewModel { UserViewModel(get()) }
		viewModel { (user: QiitaUser) -> UserItemListViewModel(user, get()) }
		factory { UserItemListAdapter() }

		factory { UserListAdapter() }
		viewModel { (mode: FollowListMode,user: QiitaUser) -> UserFollowListViewModel(mode, user, get()) }
	}
