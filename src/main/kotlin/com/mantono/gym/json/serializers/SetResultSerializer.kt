package com.mantono.gym.json.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.mantono.gym.SetResult
import com.mantono.gym.weightOf
import java.time.Instant

object SetResultDeserializer: JsonDeserializer<SetResult>()
{
	override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SetResult
	{
		val node: JsonNode = p.codec.readTree(p)
		val repetitions: Int = node["repetitions"].asInt()
		val amount: Double = node["amount"].asDouble()
		val unit: String = node["unit"].asText()
		val timestamp: Instant = Instant.ofEpochSecond(node["seconds"].asLong(), node["nanos"].asLong())
		return SetResult(repetitions, weightOf(amount, unit), timestamp)
	}

}

object SetResultSerializer: JsonSerializer<SetResult>()
{
	override fun serialize(value: SetResult, gen: JsonGenerator, serializers: SerializerProvider)
	{
		gen.writeStartObject()
		gen.writeNumberField("repetitions", value.repetitions)
		WeightSerializer.serialize(value.weight, gen, serializers)
		gen.writeNumberField("seconds", value.timestamp.epochSecond)
		gen.writeNumberField("nanos", value.timestamp.nano)
		gen.writeEndObject()
	}
}