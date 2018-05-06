package com.mantono.gym.json.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.mantono.gym.Kilogram
import com.mantono.gym.Pound
import com.mantono.gym.Weight
import com.mantono.gym.Zero
import com.mantono.gym.weightOf

object WeightDeserializer: JsonDeserializer<Weight>()
{
	override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Weight
	{
		val node: JsonNode = p.codec.readTree(p)
		System.out.println(node)
		val amount: Double = node["amount"].asDouble()
		val unit: String = node["unit"].asText()
		return weightOf(amount, unit)
	}

}

object WeightSerializer: JsonSerializer<Weight>()
{
	override fun serialize(value: Weight, gen: JsonGenerator, serializers: SerializerProvider)
	{
		val (unit: String, amount: Double) = when(value)
		{
			Zero -> "zero" to 0.0
			is Kilogram -> "kilogram" to value.kilograms
			is Pound -> "pound" to value.pounds

		}
		writeWeight(gen, unit, amount)
	}

	private fun writeWeight(gen: JsonGenerator, unit: String, amount: Double)
	{
		gen.writeStartObject()
		gen.writeStringField("unit", unit)
		gen.writeNumberField("amount", amount)
		gen.writeEndObject()
	}
}