package com.sorrowblue.qiichan.domains.item

import android.net.Uri
import android.os.Parcelable
import androidx.annotation.Keep
import com.sorrowblue.qiichan.domains.tag.SimpleQiitaTag
import com.soywiz.klock.DateTimeTz
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class QiitaSimpleItem(
	val id: QiitaItemId,
	val updateAt: DateTimeTz,
	val createAt: DateTimeTz,
	val title: String,
	val tag: SimpleQiitaTag,
	val author: String,
	val url: Uri
): Parcelable
