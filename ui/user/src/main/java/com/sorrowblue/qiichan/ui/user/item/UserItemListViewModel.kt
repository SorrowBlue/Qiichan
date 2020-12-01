package com.sorrowblue.qiichan.ui.user.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.toLiveData
import com.sorrowblue.qiichan.domains.user.QiitaUser
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.parameter.parametersOf

internal class UserItemListViewModel(user: QiitaUser, val adapter: UserItemListAdapter) :
	ViewModel(), KoinComponent {
	val pagedList =
		get<UserItemListDataSource.Factory> { parametersOf(viewModelScope, user) }.toLiveData(6)
}
