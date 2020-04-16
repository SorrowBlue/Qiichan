package com.sorrowblue.qiichan.domains.item

import android.net.Uri
import android.os.Parcelable
import androidx.annotation.Keep
import com.sorrowblue.qiichan.domains.DateTimeTzSerializer
import com.sorrowblue.qiichan.domains.UriSerializer
import com.sorrowblue.qiichan.domains.user.UserId
import com.soywiz.klock.DateTimeTz
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Parcelize
@Serializable
data class TrendItem private constructor(
	@SerialName("uuid") private val _id: String,
	@Serializable(DateTimeTzSerializer::class) val createdAt: DateTimeTz,
	val likesCount: Int,
	val title: String,
	val author: Author,
	val isNewArrival: Boolean = false
) : Parcelable {
	val id get() = QiitaItemId(_id)

	@Keep
	@Parcelize
	@Serializable
	data class Author private constructor(
		@SerialName("urlName") private val _id: String,
		@Serializable(UriSerializer::class) val profileImageUrl: Uri
	) : Parcelable {
		val id get() = UserId(_id)
	}
}
