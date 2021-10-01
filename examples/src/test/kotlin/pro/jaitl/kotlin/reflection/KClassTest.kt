package pro.jaitl.kotlin.reflection

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

class KClassTest {
    @Test
    fun testFromType() {
        val kClass: KClass<String> = String::class
        assertEquals("kotlin.String", kClass.qualifiedName)
    }

    @Test
    fun testFromInstance() {
        val str = "my test"
        val kClass: KClass<out String> = str::class
        assertEquals("kotlin.String", kClass.qualifiedName)
        assertEquals(String::class, kClass)
    }

    @Test
    @ExperimentalStdlibApi()
    fun testFromKType() {
        val kType: KType = typeOf<String>()
        val kClass: KClass<String> = kType.classifier as KClass<String>
        assertEquals("kotlin.String", kClass.qualifiedName)
        assertEquals(String::class, kClass)
    }
}
