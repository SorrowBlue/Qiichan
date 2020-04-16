package com.sorrowblue.qiichan.ui.user.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.toLiveData
import com.sorrowblue.qiichan.domains.user.QiitaUser

internal class UserItemListViewModel(user: QiitaUser, val adapter: UserItemListAdapter) : ViewModel() {
	val pagedList = UserItemListDataSource.Factory(viewModelScope.coroutineContext, user).toLiveData(10)
}
