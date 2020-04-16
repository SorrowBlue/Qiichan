package com.sorrowblue.qiichan.data

import kotlinx.serialization.*

@Serializable
data class AccessToken(
	val client_id: String,
	val token: String,
	val scopes: List<Scope>
) {
	enum class Scope {
		read_qiita, read_qiita_team,
		write_qiita, write_qiita_team
	}
}

object ScopeSerializer : KSerializer<List<AccessToken.Scope>> {
	override val descriptor: SerialDescriptor
		get() = PrimitiveDescriptor("ScopeSerializer", PrimitiveKind.STRING)


	override fun deserialize(decoder: Decoder): List<AccessToken.Scope> {
		return decoder.decodeString().split(",").map { AccessToken.Scope.valueOf(it) }
	}

	override fun serialize(encoder: Encoder, value: List<AccessToken.Scope>) {
		encoder.encodeString(value.joinToString(",", transform = { it.name }))
	}
}
