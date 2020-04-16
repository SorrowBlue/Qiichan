package com.sorrowblue.qiichan.data.user

import com.sorrowblue.qiichan.domains.user.UserRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val dataUserModule
	get() = module {
		single { UserRepositoryImp(get()) } bind UserRepository::class
	}
