package com.sorrowblue.qiichan.ui.user.follow

import androidx.lifecycle.*
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.sorrowblue.qiichan.domains.user.QiitaUser
import com.sorrowblue.qiichan.ui.NetworkState
import org.koin.core.KoinComponent
import org.koin.core.get

internal class UserFollowListViewModel(
	mode: FollowListMode,
	user: QiitaUser,
	val adapter: UserListAdapter
) :
	ViewModel(), KoinComponent {

	val isEmpty: LiveData<Boolean>
	val isLoading: LiveData<Boolean>
	val pagedList: LiveData<PagedList<QiitaUser>>

	init {
		val factory = UserFollowListDataSource.Factory(viewModelScope, mode, user, get())
		isLoading = factory.dataSource.switchMap(UserFollowListDataSource::networkState)
			.map(NetworkState::isLoading)
		isEmpty = factory.dataSource.switchMap(UserFollowListDataSource::isEmpty)
		pagedList = factory.toLiveData(8)
	}
}
