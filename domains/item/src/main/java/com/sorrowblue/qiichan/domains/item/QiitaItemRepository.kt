package com.sorrowblue.qiichan.domains.item

import androidx.annotation.IntRange
import com.sorrowblue.qiichan.domains.tag.QiitaTagId
import com.sorrowblue.qiichan.domains.user.UserId
import com.soywiz.klock.DateTimeTz

interface QiitaItemRepository {
	suspend fun items(): List<QiitaItem>
	suspend fun trends(): List<TrendItem>
	suspend fun item(itemId: QiitaItemId): QiitaItem
	suspend fun tagFeeds(tagId: QiitaTagId): List<QiitaSimpleItem>
	suspend fun userItems(
		userId: UserId,
		@IntRange(from = 1, to = 100) page: Int = 1,
		@IntRange(from = 1, to = 100) perPage: Int = 20
	): List<QiitaItem>

	suspend fun items(
		tagId: QiitaTagId,
		@IntRange(from = 1, to = 100) page: Int,
		@IntRange(from = 1, to = 100) perPage: Int
	): List<QiitaItem>

	fun trendTime(): DateTimeTz
}
