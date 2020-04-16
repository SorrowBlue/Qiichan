package com.sorrowblue.qiichan.domains

import android.net.Uri
import androidx.core.net.toUri
import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.*


object UriSerializer : KSerializer<Uri> {
	override val descriptor = PrimitiveDescriptor("UriSerializer", PrimitiveKind.STRING)
	override fun deserialize(decoder: Decoder): Uri = decoder.decodeString().toUri()
	override fun serialize(encoder: Encoder, value: Uri) = encoder.encodeString(value.toString())
}

object DateTimeTzSerializer : KSerializer<DateTimeTz> {
	override val descriptor: SerialDescriptor =
		PrimitiveDescriptor("DateTimeSerializer", PrimitiveKind.STRING)

	override fun deserialize(decoder: Decoder): DateTimeTz =
		DateTime.fromString(decoder.decodeString())

	override fun serialize(encoder: Encoder, value: DateTimeTz) {
		encoder.encodeString(value.format(DateFormat.DEFAULT_FORMAT))
	}
}
