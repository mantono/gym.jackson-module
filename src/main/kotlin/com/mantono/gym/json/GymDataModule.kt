package com.mantono.gym.json

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import com.mantono.gym.SetResult
import com.mantono.gym.Weight
import com.mantono.gym.Zero
import com.mantono.gym.json.serializers.IdentifierDeserializer
import com.mantono.gym.json.serializers.WeightDeserializer
import com.mantono.gym.json.serializers.IdentifierSerializer
import com.mantono.gym.json.serializers.SetResultDeserializer
import com.mantono.gym.json.serializers.SetResultSerializer
import com.mantono.gym.json.serializers.WeightSerializer
import com.mantono.solo.api.Identifier

val gymDataModule: Module = run {
	SimpleModule().apply {
		addDeserializer(Weight::class.java, WeightDeserializer)
		addSerializer(Weight::class.java, WeightSerializer)
		addDeserializer(Identifier::class.java, IdentifierDeserializer)
		addSerializer(Identifier::class.java, IdentifierSerializer)
		addDeserializer(SetResult::class.java, SetResultDeserializer)
		addSerializer(SetResult::class.java, SetResultSerializer)
	}
}

val mapper: ObjectMapper = ObjectMapper()
		.registerKotlinModule()
		.registerModule(gymDataModule)
		.registerModule(ParameterNamesModule())
		.registerModule(Jdk8Module())
		.registerModule(JavaTimeModule())