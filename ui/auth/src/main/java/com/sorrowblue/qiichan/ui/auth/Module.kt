package com.sorrowblue.qiichan.ui.auth

import com.sorrowblue.qiichan.ui.auth.login.LoginViewModel
import com.sorrowblue.qiichan.ui.auth.response.AuthResponseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiAuthModule
	get() = module {
		viewModel { AuthUserViewModel(get()) }
		viewModel { LoginViewModel(get()) }
		viewModel { AuthResponseViewModel(get()) }
	}
