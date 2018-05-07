package com.mantono.gym.json

import com.mantono.gym.Kilogram
import com.mantono.gym.Pound
import com.mantono.gym.Weight
import com.mantono.gym.Zero
import org.junit.jupiter.api.Test

class WeightTest
{
	@Test
	fun testKilogram()
	{
		val weight: Weight = Kilogram(28.0)
		serializationTest(weight)
	}

	@Test
	fun testPound()
	{
		val weight: Weight = Pound(20f)
		serializationTest(weight)
	}

	@Test
	fun testZero()
	{
		serializationTest<Weight>(Zero)
	}
}