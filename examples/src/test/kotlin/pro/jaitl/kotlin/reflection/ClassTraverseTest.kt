package pro.jaitl.kotlin.reflection

import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField
import kotlin.test.Test

class ClassTraverseTest {
    annotation class FirstTestAnnotation
    annotation class SecondTestAnnotation

    @FirstTestAnnotation
    @SecondTestAnnotation
    class MyClass(
        @field:FirstTestAnnotation val int: Int,
        @field:SecondTestAnnotation val string: String
    ) {

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

    @Test
    fun testTraverseAnnotationsForClass() {
        val clazz = MyClass::class
        clazz.annotations.forEach { println("${it.annotationClass}") }
    }

    @Test
    fun testTraverseAnnotationsForFields() {
        val clazz = MyClass::class
        val properties = clazz.memberProperties

        properties.forEach { property ->
            println("property name: ${property.name}")
            property.javaField?.annotations?.forEach { println("annotation class: ${it.annotationClass}") }
        }
    }
}
