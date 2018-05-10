package com.mantono.gym.json

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.lang.Exception

internal inline fun <reified T> serializationTest(obj: T): T
{
	assertTrue(mapper.canSerialize(T::class.java))
	val serialized = mapper.writeValueAsBytes(obj)
	try
	{
		val deserialized: T = mapper.readValue(serialized, T::class.java)
		assertEquals(obj, deserialized)
		return deserialized
	}
	catch(e: Exception)
	{
		prettyPrint(serialized)
		throw e
	}
}

fun prettyPrint(obj: ByteArray)
{
	val json = mapper.readValue<Any>(obj, Any::class.java)
	val output: String = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json)
	System.out.println(output)
}
