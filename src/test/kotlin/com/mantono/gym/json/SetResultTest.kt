package com.mantono.gym.json

import com.mantono.gym.Kilogram
import com.mantono.gym.Pound
import com.mantono.gym.SetResult
import com.mantono.gym.Zero
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.Instant

class SetResultTest
{
	@Test
	fun encodeAndDecodeSetOfSetResultWithJackson()
	{
		val sets: Set<SetResult> = setOf(
				SetResult(3, Kilogram(14.0), 22L),
				SetResult(3, Pound(14.0), Instant.ofEpochSecond(10, 5)),
				SetResult(1, Zero, Instant.now())
		)

		assertTrue(mapper.canSerialize(Set::class.java))
		val serialized = mapper.writeValueAsBytes(sets)
		val deserialized: Set<SetResult> = mapper.readValue(serialized, Set::class.java) as Set<SetResult>
		assertEquals(deserialized, deserialized)
	}

	@Test
	fun encodeAndDecodeSetResultWithJackson()
	{
		val kilogramResult = SetResult(3, Kilogram(14.0), 22L)
		val poundResult = SetResult(3, Pound(14.0), Instant.ofEpochSecond(10, 5))
		val zeroResult = SetResult(1, Zero, Instant.now())
		serializationTest(kilogramResult)
		serializationTest(poundResult)
		serializationTest(zeroResult)
	}
}