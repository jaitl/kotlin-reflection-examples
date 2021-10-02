package pro.jaitl.kotlin.reflection

import kotlin.reflect.KClass

fun printType(clazz: KClass<*>) {
    println(clazz.qualifiedName)
}

inline fun <reified T>printType() {
    println(T::class.qualifiedName)
}
