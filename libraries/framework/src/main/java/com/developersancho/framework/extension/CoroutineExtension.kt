package com.developersancho.framework.extension

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.TimeUnit

fun <T> delayFlow(timeout: Long, value: T): Flow<T> = flow {
    delay(timeout)
    emit(value)
}

fun flowInterval(interval: Long, timeUnit: TimeUnit = TimeUnit.MILLISECONDS): Flow<Int> {
    val delayMillis = timeUnit.toMillis(interval)
    return channelFlow {
        var tick = 0
        send(tick)
        while (true) {
            delay(delayMillis)
            send(++tick)
        }
    }
}

fun <T> CoroutineScope.lazyAsync(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> = lazy {
    async(start = CoroutineStart.LAZY) {
        block.invoke(this)
    }
}

/**
 * Alias to stateIn with defaults
 */
fun <T> Flow<T>.stateInDefault(
    scope: CoroutineScope,
    initialValue: T,
    started: SharingStarted = SharingStarted.WhileSubscribed(5000),
) = stateIn(scope, started, initialValue)

/**
 * Delays given [target]'s emission for [timeMillis]
 * i.e skips emission of [target] if something else is emitted before [timeMillis]
 */
@OptIn(ExperimentalCoroutinesApi::class)
fun <T> Flow<T>.delayItem(timeMillis: Long, target: T) = mapLatest {
    if (it == target) {
        delay(timeMillis)
        it
    } else it
}