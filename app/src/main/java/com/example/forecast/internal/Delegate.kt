package com.example.forecast.internal

import kotlinx.coroutines.*

fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T) : Lazy<Deferred<T>>{
    // return Lazy<XXX>
    return lazy{
        // return a Deferred<T>
        GlobalScope.async (start = CoroutineStart.LAZY){
            block.invoke(this) // return T
        }
    }
}