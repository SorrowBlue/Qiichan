package com.sorrowblue.qiichan.ui.auth.login

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.sorrowblue.qiichan.domains.auth.QiitaAuthService
import com.sorrowblue.qiichan.domains.auth.QiitaScope
import com.sorrowblue.qiichan.ui.lifecycle.SingleLiveEvent

internal class LoginViewModel(private val service: QiitaAuthService) : ViewModel() {

	val uri = SingleLiveEvent<Uri>()
	val isLogin get() = service.authUser != null

	fun onClickQiita() {
		uri.value = service.authorizeUri(QiitaScope.QIITA)
	}

	fun onClickQiitaTeam() {
		uri.value = service.authorizeUri(QiitaScope.QIITA_TEAM)
	}
}
