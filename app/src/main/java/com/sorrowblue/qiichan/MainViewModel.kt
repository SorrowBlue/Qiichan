package com.sorrowblue.qiichan

import android.content.Context
import androidx.core.app.AppLaunchChecker
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.sorrowblue.qiichan.domains.auth.QiitaAuthService
import com.sorrowblue.qiichan.ui.lifecycle.SingleLiveEvent
import kotlinx.coroutines.launch

internal class MainViewModel(context: Context, private val service: QiitaAuthService) :
	ViewModel() {

	val navigation = SingleLiveEvent<NavDirections>()

	init {
		if (!AppLaunchChecker.hasStartedFromLauncher(context)) {
			navigation.value = MobileNavigationDirections.actionGlobalAuthLoginNavigation()
		} else {
			viewModelScope.launch {
				kotlin.runCatching { service.authenticatedUser() }
			}
		}
	}
}
