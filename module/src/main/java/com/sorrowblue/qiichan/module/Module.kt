package com.sorrowblue.qiichan.module

import com.sorrowblue.qiichan.data.auth.dataAuthModule
import com.sorrowblue.qiichan.data.dataModule
import com.sorrowblue.qiichan.data.item.dataItemModule
import com.sorrowblue.qiichan.data.tag.dataTagModule
import com.sorrowblue.qiichan.data.user.dataUserModule
import com.sorrowblue.qiichan.ui.auth.uiAuthModule
import com.sorrowblue.qiichan.ui.item.uiItemModule
import com.sorrowblue.qiichan.ui.tag.uiTagModule
import com.sorrowblue.qiichan.ui.tagfeed.uiTagFeedModel
import com.sorrowblue.qiichan.ui.trend.uiTrendViewModel
import com.sorrowblue.qiichan.ui.uiModule
import com.sorrowblue.qiichan.ui.user.uiUserModule
import org.koin.core.module.Module

val uiModules: List<Module> = listOf(
	uiModule,
	uiUserModule,
	uiItemModule,
	uiAuthModule,
	uiTrendViewModel,
	uiTagModule,
	uiTagFeedModel
)

val dataModules: List<Module> = listOf(
	dataModule,
	dataTagModule,
	dataAuthModule,
	dataUserModule,
	dataItemModule
)
