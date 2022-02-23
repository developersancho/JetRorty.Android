package com.developersancho.framework.network

// simple offline first logic
suspend inline fun <R> networkWithCache(
    crossinline createCall: suspend () -> R,
    crossinline loadFromLocal: suspend () -> R?,
    crossinline shouldFetch: suspend (R?) -> Boolean,
    crossinline saveCallResult: suspend (R) -> Unit
): R {
    val cache = loadFromLocal()
    val shouldFetchFromDb = shouldFetch(cache)

    return if (cache == null || shouldFetchFromDb) {
        val result = createCall()
        saveCallResult(result)
        result
    } else {
        cache
    }
}