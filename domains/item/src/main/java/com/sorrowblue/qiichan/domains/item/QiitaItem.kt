package com.sorrowblue.qiichan.domains.item

import android.net.Uri
import android.os.Parcelable
import com.sorrowblue.qiichan.domains.DateTimeTzSerializer
import com.sorrowblue.qiichan.domains.UriSerializer
import com.sorrowblue.qiichan.domains.user.QiitaUser
import com.soywiz.klock.DateTimeTz
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class QiitaItem private constructor(
	@SerialName("rendered_body") val renderedBody: String = "",
	val body: String = "",
	val coediting: Boolean = false,
	@SerialName("comments_count") val commentsCount: Int = 0,
	@SerialName("created_at") @Serializable(DateTimeTzSerializer::class) val createdAt: DateTimeTz = DateTimeTz.nowLocal(),
	@SerialName("id") private val _id: String = "",
	@SerialName("likes_count") val likesCount: Int = 0,
	val private: Boolean = false,
	val tags: List<QiitaItemTag> = emptyList(),
	val title: String = "",
	@SerialName("updated_at") @Serializable(DateTimeTzSerializer::class) val updatedAt: DateTimeTz = DateTimeTz.nowLocal(),
	@Serializable(UriSerializer::class) val url: Uri = Uri.EMPTY,
	val user: QiitaUser = QiitaUser(),
	@SerialName("page_views_count") val pageViewsCount: Int? = 0
): Parcelable {
	//	@formatter:off
	constructor(id: QiitaItemId, renderedBody: String, body: String, title: String, url: Uri, user: QiitaUser, pageViewsCount: Int, commentsCount: Int, likesCount: Int, private: Boolean, tags: List<QiitaItemTag>, createdAt: DateTimeTz, updatedAt: DateTimeTz) :
			this(renderedBody = renderedBody, body = body, commentsCount = commentsCount, createdAt = createdAt, _id = id.value, likesCount = likesCount, private = private, tags = tags, title = title, updatedAt = updatedAt, url = url, user = user, pageViewsCount = pageViewsCount)
	//	@formatter:on
	val id: QiitaItemId get() = QiitaItemId(_id)

	val tagsName get() = tags.joinToString(", ") { it.id.value }
}

