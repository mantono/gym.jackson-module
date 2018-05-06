package com.mantono.gym.json

import com.mantono.gym.generateIdBlocking
import com.mantono.solo.api.Identifier
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class IdentifierTest
{
	@Test
	fun encodeAndDecodeIdentifier()
	{
		val idBefore: Identifier = generateIdBlocking()
		val idAfter: Identifier = serializationTest(idBefore)
		assertArrayEquals(idBefore.asBytes(), idAfter.asBytes())
	}
}