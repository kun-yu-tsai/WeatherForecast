package com.example.forecast

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    operator fun Int.invoke() {
        println(this)
    }
    @Test
    fun addition_isCorrect() {
        println(test("null"))
//        println(Demo())
        val demo = Demo()
        println(demo)
        3()
    }

    private fun test(string: String?): String {
        return string ?: "good".also { println(it) }
    }


    class Demo private constructor(private val name: String){
//        operator fun invoke(){
//            println("123")
//        }

        companion object{
            operator fun invoke(): String {
                return "123"
            }
        }
    }
}
