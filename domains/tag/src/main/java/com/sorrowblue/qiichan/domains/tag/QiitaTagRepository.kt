package com.sorrowblue.qiichan.domains.tag

import androidx.annotation.IntRange
import com.sorrowblue.qiichan.domains.user.UserId

interface QiitaTagRepository {

	suspend fun tags(
		@IntRange(from = 1.toLong(), to = 100.toLong()) page: Int = 1,
		@IntRange(from = 1.toLong(), to = 100.toLong()) perPage: Int = 20,
		sort: QiitaTag.Sort? = null
	): List<QiitaTag>

	suspend fun tag(id: QiitaTagId): QiitaTag

	suspend fun followingTags(
		userId: UserId,
		@IntRange(from = 1L, to = 100L) page: Int = 1,
		@IntRange(from = 1L, to = 100L) perPage: Int = 20
	): List<QiitaTag>

	suspend fun unfollow(tagId: QiitaTagId)
	suspend fun follow(tagId: QiitaTagId)
	suspend fun isFollowing(tagId: QiitaTagId): QiitaTag
	suspend fun simpleTag(id: QiitaTagId): SimpleQiitaTag
}

