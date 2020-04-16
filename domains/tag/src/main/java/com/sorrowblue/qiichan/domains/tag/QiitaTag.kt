package com.sorrowblue.qiichan.domains.tag

import android.net.Uri
import android.os.Parcelable
import androidx.annotation.Keep
import com.sorrowblue.qiichan.domains.UriSerializer
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Keep
@Parcelize
@Serializable
data class QiitaTag private constructor(
	@SerialName("followers_count") val followersCount: Int,
	@SerialName("icon_url") @Serializable(UriSerializer::class) val iconUrl: Uri,
	@SerialName("id") private val _id: String,
	@SerialName("items_count") val itemsCount: Int
) : Parcelable {

	constructor(id: QiitaTagId, iconUrl: Uri, followersCount: Int, itemsCount: Int) :
			this(followersCount, iconUrl, id.value, itemsCount)

	val id get() = QiitaTagId(_id)

	enum class Sort {
		COUNT, NAME;

		val key = name.toLowerCase(Locale.getDefault())
	}
}

@Parcelize
data class SimpleQiitaTag(val id: QiitaTagId, val iconUrl: Uri): Parcelable
