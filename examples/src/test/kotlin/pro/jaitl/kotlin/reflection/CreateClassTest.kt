package pro.jaitl.kotlin.reflection

import java.time.Instant
import kotlin.reflect.full.primaryConstructor
import kotlin.test.Test
import kotlin.test.assertEquals

sealed class Adt {
    data class AdtOne(val double: Double, val string: String)
}

class CreateClassTest {
    data class SimpleClass(val int: Int, val string: String, val instant: Instant)

    @Test
    fun testCreateByConstructorByParamsArray() {
        val paramsData = arrayOf(1234, "test", Instant.now())

        val clazz = SimpleClass::class
        val constructor = clazz.primaryConstructor!!

        val dataClass: SimpleClass = constructor.call(*paramsData)
        println(dataClass)
    }

    @Test
    fun testCreateByConstructorByParamsNames() {
        val paramsData = mapOf("int" to 1234, "string" to "test", "instant" to Instant.now())

        val clazz = SimpleClass::class
        val constructor = clazz.primaryConstructor!!
        val args = constructor.parameters.associateWith { paramsData[it.name] }

        val dataClass: SimpleClass = constructor.callBy(args)
        println(dataClass)
    }

    @Test
    fun testCreateByName() {
        val expectedClass = Adt.AdtOne(1.11, "test")
        val javaName = expectedClass::class.java.name
        println("kotlin name: ${expectedClass::class.qualifiedName}")
        println("java name: $javaName")

        val clazz = Class.forName(javaName).kotlin
        val constructor = clazz.primaryConstructor!!

        val actualClass = constructor.call(expectedClass.double, expectedClass.string)
        println(actualClass)

        assertEquals(Adt.AdtOne::class, actualClass::class)
        assertEquals(expectedClass, actualClass)
    }
}
