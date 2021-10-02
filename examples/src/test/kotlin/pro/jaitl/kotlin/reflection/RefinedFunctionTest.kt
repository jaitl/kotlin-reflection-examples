package pro.jaitl.kotlin.reflection

import kotlin.reflect.KClass
import kotlin.test.Test

class RefinedFunctionTest {

    fun printType(clazz: KClass<*>) {
        println(clazz.qualifiedName)
    }

    inline fun <reified T>printType() {
        println(T::class.qualifiedName)
    }

    inline fun <reified T>printType(obj: T) {
        println(T::class.qualifiedName)
    }

    @Test
    fun testSentKTypeAsArgument() {
        printType(String::class)
    }

    @Test
    fun tesGetKTypeFromRefinedParam() {
        printType<String>()
    }

    @Test
    fun tesGetKTypeFromArgument() {
        printType("test")
    }
}
