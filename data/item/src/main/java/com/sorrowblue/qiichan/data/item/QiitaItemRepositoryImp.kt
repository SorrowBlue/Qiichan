package com.sorrowblue.qiichan.data.item

import android.util.Log
import androidx.core.content.edit
import androidx.core.net.toUri
import com.sorrowblue.qiichan.data.AndroidClient
import com.sorrowblue.qiichan.data.SecurePreferences
import com.sorrowblue.qiichan.data.item.comment.QiitaCommentRepositoryImp
import com.sorrowblue.qiichan.domains.item.*
import com.sorrowblue.qiichan.domains.item.comment.QiitaCommentRepository
import com.sorrowblue.qiichan.domains.tag.QiitaTagId
import com.sorrowblue.qiichan.domains.tag.QiitaTagRepository
import com.sorrowblue.qiichan.domains.user.UserId
import com.soywiz.klock.*
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.boolean
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.koin.dsl.bind
import org.koin.dsl.module

internal class QiitaItemRepositoryImp(
	private val client: AndroidClient,
	private val preferences: SecurePreferences,
	private val tagRepo: QiitaTagRepository
) : QiitaItemRepository {

	override suspend fun items(): List<QiitaItem> = client.get("/items")

	override suspend fun item(itemId: QiitaItemId): QiitaItem = client.get("/items/${itemId.value}")

	@OptIn(UnstableDefault::class)
	private suspend fun scrapingTrend(): List<TrendItem> {
		return client.getPlane("https://qiita.com/")
			.let(Jsoup::parse).getElementsByAttributeValue("data-hyperapp-app", "Trend")
			.attr("data-hyperapp-props").let(Json.Default::parseJson)
			.jsonObject["trend"]?.jsonObject?.getArray("edges")
			?.mapNotNull {
				Json.parse(TrendItem.serializer(), it.jsonObject.getObject("node").toString()).copy(
					isNewArrival = it.jsonObject["isNewArrival"]!!.boolean
				)
			}
			?: emptyList()
	}

	@OptIn(UnstableDefault::class)
	private suspend fun scrapingTagFeed(tagId: QiitaTagId): List<QiitaSimpleItem> {
		val tag = tagRepo.simpleTag(tagId)
		return client.getPlane("https://qiita.com/tags/${tagId.value}/feed.atom") {}
			.let(Jsoup::parse).getElementsByTag("entry").map {
				QiitaSimpleItem(
					id = QiitaItemId(it.tag("url").text().toUri().lastPathSegment!!),
					updateAt = DateTime.fromString(it.tag("updated").text()),
					createAt = DateTime.fromString(it.tag("published").text()),
					tag = tag,
					title = it.tag("title").text(),
					author = it.tag("author").text(),
					url = it.tag("url").text().toUri()
				)
			}
	}

	override suspend fun items(tagId: QiitaTagId, page: Int, perPage: Int): List<QiitaItem> =
		client.get("/tags/${tagId.value}/items?page=$page&per_page=$perPage")

	override fun trendTime(): DateTimeTz {
		val now = DateTime.nowLocal()
		return when (now.hours) {
			in 0..4 -> now.minus(DateTimeSpan(days = 1))
				.run { DateTime.invoke(year, month, dayOfMonth, 17, 0, 0, 0) }
			in 5..16 -> now.run { DateTime.invoke(year, month, dayOfMonth, 5, 0, 0, 0) }
			else -> now.run { DateTime.invoke(year, month, dayOfMonth, 17, 0, 0, 0) }
		}.let { DateTimeTz.local(it, TimezoneOffset.local(it)) }
	}

	@OptIn(UnstableDefault::class)
	override suspend fun trends(): List<TrendItem> {
		val now = DateTime.nowLocal()
		val keyTime = when (now.hours) {
			in 0..4 -> now.minus(DateTimeSpan(days = 1))
				.run { DateTime.invoke(year, month, dayOfMonth, 17, 0, 0, 0) }
			in 5..16 -> now.run { DateTime.invoke(year, month, dayOfMonth, 5, 0, 0, 0) }
			else -> now.run { DateTime.invoke(year, month, dayOfMonth, 17, 0, 0, 0) }
		}.let { DateTimeTz.local(it, TimezoneOffset.local(it)) }.format(DateFormat.DEFAULT_FORMAT)
		val removeKey = when (now.hours) {
			in 0..4 -> now.minus(DateTimeSpan(days = 1))
				.run { DateTime.invoke(year, month, dayOfMonth, 5, 0, 0, 0) }
			in 5..16 -> now.minus(DateTimeSpan(days = 1))
				.run { DateTime.invoke(year, month, dayOfMonth, 17, 0, 0, 0) }
			else -> now.run { DateTime.invoke(year, month, dayOfMonth, 5, 0, 0, 0) }
		}.let { DateTimeTz.local(it, TimezoneOffset.local(it)) }.format(DateFormat.DEFAULT_FORMAT)
		Log.d(javaClass.simpleName, "trend time $keyTime remove $removeKey")
		return preferences.preferences.getString(keyTime, null)?.let {
			Json.parse(TrendItem.serializer().list, it)
		}?.also {
			Log.d(javaClass.simpleName, "list ${it.map(TrendItem::title)}")
		} ?: run {
			val list = scrapingTrend()
			Log.d(javaClass.simpleName, "list ${list.map(TrendItem::title)}")
			preferences.preferences.edit {
				putString(keyTime, Json.stringify(TrendItem.serializer().list, list))
				remove(removeKey)
			}
			list
		}
	}

	override suspend fun tagFeeds(tagId: QiitaTagId) = scrapingTagFeed(tagId)
	override suspend fun userItems(userId: UserId, page: Int, perPage: Int): List<QiitaItem> =
		client.get("/users/${userId.value}/items?page=$page&per_page=$perPage")
}

private fun Element.tag(s: String) = getElementsByTag(s).first()

val dataItemModule = module {
	single { QiitaItemRepositoryImp(get(), get(), get()) } bind QiitaItemRepository::class
	single { QiitaCommentRepositoryImp(get()) } bind QiitaCommentRepository::class
}
