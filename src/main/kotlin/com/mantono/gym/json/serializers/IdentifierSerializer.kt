package com.mantono.gym.json.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.mantono.solo.api.Identifier
import toBase64

object IdentifierSerializer: JsonSerializer<Identifier>()
{
	override fun serialize(value: Identifier, gen: JsonGenerator, serializers: SerializerProvider)
	{
		gen.writeStartObject()
		gen.writeStringField("base64", value.asBytes().toBase64())
		gen.writeEndObject()
	}
}

object IdentifierDeserializer: JsonDeserializer<Identifier>()
{
	override fun deserialize(parser: JsonParser, ctxt: DeserializationContext): Identifier
	{
		val node: JsonNode = parser.codec.readTree(parser)

		return object: Identifier
		{
			override fun asBytes(): ByteArray
			{
				val b64: String = node["base64"].asText()
				return java.util.Base64.getDecoder().decode(b64)
			}
		}
	}
}