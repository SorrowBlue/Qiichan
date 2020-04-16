package com.sorrowblue.qiichan.domains.item

import com.sorrowblue.qiichan.domains.DateTimeTzSerializer
import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(DateTimeTzSerializer::class)
data class QiitaGroup(
	@SerialName("created_at") val updatedAt: DateTimeTz = DateTimeTz.nowLocal(),
	val id: Int = 0,
	val name: String = "",
	val private: Boolean = false,
	@SerialName("updated_at") val createdAt: DateTimeTz = DateTimeTz.nowLocal(),
	@SerialName("url_name") val urlName: String = ""
)
