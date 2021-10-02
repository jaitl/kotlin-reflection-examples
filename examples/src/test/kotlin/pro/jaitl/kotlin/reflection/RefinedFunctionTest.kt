package pro.jaitl.kotlin.reflection

import kotlin.test.Test

class RefinedFunctionTest {
    @Test
    fun testRefined() {
        printType(String::class)
        printType<String>()
    }
}
