package com.sorrowblue.qiichan.ui.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorrowblue.qiichan.domains.user.QiitaUser
import com.sorrowblue.qiichan.domains.user.UserId
import com.sorrowblue.qiichan.domains.user.UserRepository
import com.sorrowblue.qiichan.ui.lifecycle.SingleLiveEvent
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

internal class UserViewModel(
	private val repo: UserRepository
) : ViewModel() {

	private val _user = MutableLiveData<QiitaUser?>()
	val user: LiveData<QiitaUser?> = _user
	val action = SingleLiveEvent<UserAction>()

	val isFollowing: MutableLiveData<Boolean> = MutableLiveData()

	fun follow() {
		viewModelScope.launch {
			if (isFollowing.value == true) {
				repo.unfollow(user.value!!.userId)
				isFollowing.postValue(false)
			} else {
				repo.follow(user.value!!.userId)
				isFollowing.postValue(true)
			}
		}
	}

	init {
		var def: Deferred<Boolean>? = null
		user.observeForever {
			def?.cancel()
			if (it != null) {
				viewModelScope.launch {
					def = async {
						repo.isFollowing(it.userId)
					}.also { isFollowing.postValue(it.await()) }
				}
			}
		}
	}

	fun setAction(action: UserAction) {
		this.action.value = action
	}

	fun setUser(qiitaUser: QiitaUser?) {
		qiitaUser?.let { _user.value = it }
	}

	fun setUserId(userId: UserId) {
		viewModelScope.launch {
			runCatching { repo.user(userId) }
				.onFailure { Log.d("UserViewModel", "${it.message}") }
				.getOrNull().also { _user.postValue(it) }
		}
	}
}

enum class UserAction {
	TAG, ITEM, FOLLOWING, FOLLOWED
}
