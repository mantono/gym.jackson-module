package com.mantono.gym.json

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

internal inline fun <reified T> serializationTest(obj: T): T
{
	assertTrue(mapper.canSerialize(T::class.java))
	val serialized = mapper.writeValueAsBytes(obj)
	val deserialized: T = mapper.readValue(serialized, T::class.java)
	assertEquals(obj, deserialized)
	return deserialized
}