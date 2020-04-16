package com.sorrowblue.qiichan.ui.auth

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sorrowblue.qiichan.domains.auth.QiitaAuthService
import com.sorrowblue.qiichan.domains.auth.QiitaAuthUser
import com.sorrowblue.qiichan.ui.lifecycle.SingleLiveEvent

internal class AuthUserViewModel(service: QiitaAuthService) : ViewModel() {

	val uri = SingleLiveEvent<Uri>()
	val user: LiveData<QiitaAuthUser?> = liveData {
		this.emit(runCatching { service.authenticatedUser() }.getOrNull())
	}
	val action = SingleLiveEvent<UserAction>()

	fun setAction(action: UserAction) {
		this.action.value = action
	}
}

enum class UserAction {
	TAG, ITEM, FOLLOWING, FOLLOWED
}
