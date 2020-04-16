package com.sorrowblue.qiichan.domains.item.comment

import android.os.Parcelable
import androidx.annotation.Keep
import com.sorrowblue.qiichan.domains.DateTimeTzSerializer
import com.sorrowblue.qiichan.domains.user.QiitaUser
import com.soywiz.klock.DateTimeTz
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

inline class QiitaCommentId(val value: String = "")

@Keep
@Parcelize
@Serializable
data class QiitaComment private constructor(
	val body: String,
	@Serializable(DateTimeTzSerializer::class) @SerialName("created_at") val createdAt: DateTimeTz,
	@SerialName("id") private val _id: String,
	@SerialName("rendered_body") val renderedBody: String,
	@Serializable(DateTimeTzSerializer::class) @SerialName("updated_at") val updateAt: DateTimeTz,
	val user: QiitaUser
) : Parcelable {
	val id get() = QiitaCommentId(_id)

	@Serializable
	data class UpdateBody(val body: String)

}
