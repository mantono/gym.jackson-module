package com.mantono.gym.json.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.mantono.gym.SetResult
import java.time.Instant

object SetResultDeserializer: JsonDeserializer<SetResult>()
{
	override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SetResult
	{
		val node: JsonNode = p.codec.readTree(p)
		System.out.println(node)
		TODO()
		val repetitions: Int = node["repetitions"].asInt()
		val unit: String = node["unit"].asText()
		//return weightOf(amount, unit)
	}

}

object SetResultSerializer: JsonSerializer<SetResult>()
{
	override fun serialize(value: SetResult, gen: JsonGenerator, serializers: SerializerProvider)
	{
		gen.writeStartObject()
		gen.writeNumberField("repetitions", value.repetitions)
		WeightSerializer.serialize(value.weight, gen, serializers)
		serializers.findValueSerializer(Instant::class.java).let { it.serialize(value.timestamp, gen, serializers) }
		gen.writeEndObject()
	}
}