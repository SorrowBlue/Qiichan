package com.sorrowblue.qiichan.domains.item

import android.os.Parcelable
import androidx.annotation.Keep
import com.sorrowblue.qiichan.domains.tag.QiitaTagId
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Parcelize
@Serializable
data class QiitaItemTag private constructor(
	@Keep
	val versions: List<String> = emptyList(),
	@SerialName("name") private val _name: String = ""
) : Parcelable {
	constructor(id: QiitaTagId, versions: List<String>) : this(versions, id.value)

	val id get() = QiitaTagId(_name)
}
