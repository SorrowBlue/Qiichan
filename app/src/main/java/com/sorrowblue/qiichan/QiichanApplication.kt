package com.sorrowblue.qiichan

import android.app.Application
import com.sorrowblue.qiichan.module.dataModules
import com.sorrowblue.qiichan.module.uiModules
import com.sorrowblue.qiichan.ui.settings.Settings
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

@Suppress("unused")
class QiichanApplication : Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidContext(this@QiichanApplication)
			modules(appModule + dataModules + uiModules)
		}
		Settings.applyDarkMode(this)
	}
}

private val appModule
	get() = module {
		viewModel { MainViewModel(androidContext(), get()) }
	}
