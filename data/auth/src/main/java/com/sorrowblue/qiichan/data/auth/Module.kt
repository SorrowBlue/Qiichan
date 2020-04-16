package com.sorrowblue.qiichan.data.auth

import com.sorrowblue.qiichan.data.AndroidClient
import com.sorrowblue.qiichan.domains.auth.QiitaAuthService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val dataAuthModule
	get() = module {
		single { SecureService(androidContext()) }
		single { AccessTokenService(service = get()) }
		single {
			QiitaAuthServiceImp(AndroidClient(androidContext()), get(), get())
		} bind QiitaAuthService::class
	}
