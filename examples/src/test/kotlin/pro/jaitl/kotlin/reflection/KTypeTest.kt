package pro.jaitl.kotlin.reflection

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection
import kotlin.reflect.KVariance
import kotlin.reflect.full.createType
import kotlin.reflect.full.defaultType
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

class KTypeTest {
    @Test
    fun testFromKClassSimple() {
        val kClass: KClass<String> = String::class
        val kType: KType = kClass.createType()
        println(kType) // kotlin.String
    }

    @Test
    fun testFromKClassGenericWithTypeArgument() {
        // type for string
        val kClassString: KClass<String> = String::class
        val kTypeString: KType = kClassString.createType()

        // type for list with strings
        val kClass: KClass<List<*>> = List::class
        val kType: KType = kClass.createType(listOf(KTypeProjection(KVariance.INVARIANT, kTypeString)))
        println(kType) // kotlin.collections.List<kotlin.String>
    }

    @Test
    fun testFromKClassGenericWithStarTypeProjector() {
        val kClass: KClass<List<*>> = List::class
        val kType: KType = kClass.starProjectedType
        assertEquals(KTypeProjection(null, null), kType.arguments.first())
        println(kType) // kotlin.collections.List<*>
    }

    @Test
    @ExperimentalStdlibApi
    fun testFromTypeOfWithSimpleType() {
        val kType = typeOf<String>()
        println(kType) // kotlin.String
    }

    @Test
    @ExperimentalStdlibApi
    fun testFromTypeOfWithGenericType() {
        val kType = typeOf<List<String>>()
        println(kType) // kotlin.collections.List<kotlin.String>
    }

    @Test
    fun testFromKParameter() {
        data class MyData(val list: List<String>)

        val clazz = MyData::class
        val constructor = clazz.primaryConstructor!!
        val kType: KType = constructor.parameters.first().type
        println(kType) // kotlin.collections.List<kotlin.String>
    }

    @Test
    fun testFromKProperty() {
        data class MyData(val list: List<String>)

        val clazz = MyData::class
        val parameters = clazz.memberProperties
        val kType: KType = parameters.first().returnType
        println(kType) // kotlin.collections.List<kotlin.String>
    }
}