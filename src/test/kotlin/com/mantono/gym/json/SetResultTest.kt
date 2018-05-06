package com.mantono.gym.json

import com.mantono.gym.Kilogram
import com.mantono.gym.Pound
import com.mantono.gym.SetResult
import com.mantono.gym.Zero
import org.junit.jupiter.api.Test
import java.time.Instant

class SetResultTest
{
	@Test
	fun encodeAndDecodeSetOfSetResultWithJackson()
	{
		val sets: Set<SetResult> = setOf(
				SetResult(3, Kilogram(14.0), 22L),
				SetResult(3, Pound(14.0), 23L),
				SetResult(1, Zero, Instant.now())
		)
		serializationTest<Set<SetResult>>(sets)
	}
}