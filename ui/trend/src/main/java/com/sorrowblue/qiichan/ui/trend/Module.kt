package com.sorrowblue.qiichan.ui.trend

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiTrendViewModel get() = module {
	factory { TrendItemAdapter() }
	viewModel { TrendViewModel(get(), get()) }
}
