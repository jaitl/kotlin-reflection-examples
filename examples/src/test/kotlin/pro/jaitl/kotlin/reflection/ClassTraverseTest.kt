package pro.jaitl.kotlin.reflection

import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.test.Test

class ClassTraverseTest {

    class MyClass(val int: Int, val string: String) {

        data class InternalClassDouble(val double: Double)
        data class InternalClassString(val string: String)

        fun method1ReturnUnit() {

        }

        fun method2ReturnsString(): String = string
    }

    @Test
    fun testTraverseProperties() {
        val data = MyClass(123, "test")
        val clazz = data::class

        val properties = clazz.memberProperties

        properties.forEach { println("name: ${it.name}, value: ${it.getter.call(data)}") }
    }

    @Test
    fun testTraverseDeclaredMethods() {
        val data = MyClass(123, "test")
        val clazz = data::class

        val methods = clazz.declaredMemberFunctions

        methods.forEach { println("name: ${it.name}, returns: ${it.call(data)}") }
    }

    @Test
    fun testTraverseAllMethods() {
        val data = MyClass(123, "test")
        val clazz = data::class

        val methods = clazz.memberFunctions

        methods.forEach { println("name: ${it.name}") }
    }

    @Test
    fun testCallsMethod() {
        val data1 = MyClass(123, "test")
        val data2 = MyClass(321, "tset")

        val clazz = MyClass::class

        val equalsMethod = clazz.memberFunctions.find { it.name == "equals" }!!

        val result = equalsMethod.call(data1, data2)

        println("data1 equals data2: $result")
    }

    @Test
    fun testTraverseNestedClasses() {
        val data = MyClass(123, "test")
        val clazz = data::class

        val nestedClasses = clazz.nestedClasses

        nestedClasses.forEach { println("${it.qualifiedName}") }
    }
}
