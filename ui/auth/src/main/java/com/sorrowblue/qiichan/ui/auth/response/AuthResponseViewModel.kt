package com.sorrowblue.qiichan.ui.auth.response

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorrowblue.qiichan.domains.auth.QiitaAuthService
import com.sorrowblue.qiichan.ui.lifecycle.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class AuthResponseViewModel(private val service: QiitaAuthService) : ViewModel() {

	val isSuccess =
		SingleLiveEvent<Boolean>()

	fun method(code: String, state: String) {
		viewModelScope.launch {
			kotlin.runCatching {
				service.requestAccessTokens(code, state)
			}.onFailure {
				Log.e(
					"AuthResponseViewModel",
					"requestAccessTokens ${it.localizedMessage}"
				)
			}.getOrNull().also {
				isSuccess.postValue(it == true)
			}
		}
	}
}
