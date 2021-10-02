package pro.jaitl.kotlin.reflection

import kotlin.reflect.typeOf
import kotlin.test.Test

class GenericTypeArgumentTest {
    @Test
    @ExperimentalStdlibApi
    fun testExtractFromMap() {
        val kType = typeOf<Map<String, Int>>()
        val arguments = kType.arguments
        val keyArgument = arguments.first().type
        val valueArgument = arguments.last().type
        println("key type argument: $keyArgument") // key type argument: kotlin.String
        println("value type argument: $valueArgument") // value type argument: kotlin.Int
    }
}
