package com.mantono.gym.json

import com.mantono.gym.Exercise
import com.mantono.gym.Load
import com.mantono.gym.generateIdBlocking
import org.junit.jupiter.api.Test

class ExerciseTet
{
	@Test
	fun encodeAndDecodeExercise()
	{
		val exerciseBefore = Exercise("Squat", id = generateIdBlocking(), load = Load(2, 8))
		serializationTest(exerciseBefore)
	}
}