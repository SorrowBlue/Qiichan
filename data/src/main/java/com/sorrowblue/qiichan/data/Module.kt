package com.sorrowblue.qiichan.data

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule get() = module {
	single { AndroidClient(androidContext()) }
	single { SecurePreferences(androidContext()) }
}
