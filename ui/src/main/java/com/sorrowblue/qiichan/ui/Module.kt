package com.sorrowblue.qiichan.ui

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule get() = module {
	viewModel { UiViewModel() }
}
