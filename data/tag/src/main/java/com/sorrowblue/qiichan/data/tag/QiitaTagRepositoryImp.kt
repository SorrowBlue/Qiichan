package com.sorrowblue.qiichan.data.tag

import android.net.Uri
import androidx.annotation.IntRange
import androidx.core.content.edit
import androidx.core.net.toUri
import com.sorrowblue.qiichan.data.AndroidClient
import com.sorrowblue.qiichan.data.SecurePreferences
import com.sorrowblue.qiichan.domains.tag.QiitaTag
import com.sorrowblue.qiichan.domains.tag.QiitaTagId
import com.sorrowblue.qiichan.domains.tag.QiitaTagRepository
import com.sorrowblue.qiichan.domains.tag.SimpleQiitaTag
import com.sorrowblue.qiichan.domains.user.UserId
import org.koin.dsl.bind
import org.koin.dsl.module

internal class QiitaTagRepositoryImp(
	val client: AndroidClient,
	val preferences: SecurePreferences
) : QiitaTagRepository {

	override suspend fun tags(
		@IntRange(from = 1, to = 100) page: Int,
		@IntRange(from = 1, to = 100) perPage: Int,
		sort: QiitaTag.Sort?
	): List<QiitaTag> = client.get("/tags?page=$$page&per_page=$perPage${sort?.key?.let { "&sort=$it" }}")

	override suspend fun tag(id: QiitaTagId): QiitaTag = client.get("/tags/${id.value}")
	override suspend fun simpleTag(id: QiitaTagId): SimpleQiitaTag =
		SimpleQiitaTag(id, getIconUri(id) ?: tag(id).iconUrl.also { setIconUri(id, it) })

	override suspend fun followingTags(userId: UserId, page: Int, perPage: Int): List<QiitaTag> =
		client.get("/users/${userId.value}/following_tags?page=$page&per_page=$perPage")

	override suspend fun unfollow(tagId: QiitaTagId) =
		client.delete<Unit>("/tags/${tagId.value}/following")

	override suspend fun isFollowing(tagId: QiitaTagId) =
		client.get<QiitaTag>("/tags/${tagId.value}/following")

	override suspend fun follow(tagId: QiitaTagId) =
		client.put<Unit>("/tags/${tagId.value}/following")

	private fun getIconUri(id: QiitaTagId) =
		preferences.preferences.getString("tag.icon_url.${id.value}", null)?.toUri()

	private fun setIconUri(id: QiitaTagId, iconUri: Uri) =
		preferences.preferences.edit { putString("tag.icon_url.${id.value}", iconUri.toString()) }
}

val dataTagModule
	get() = module {
		single { QiitaTagRepositoryImp(get(), get()) } bind QiitaTagRepository::class
	}
