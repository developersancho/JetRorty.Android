package com.developersancho.framework.extension

import java.math.BigDecimal

fun Int?.orZero(): Int = this ?: 0
fun Double?.orZero(): Double = this ?: 0.0
fun Long?.orZero(): Long = this ?: 0L
fun BigDecimal?.orZero(): BigDecimal = this ?: BigDecimal.ZERO

fun Int?.orOne(): Int = this ?: 1
fun Double?.orOne(): Double = this ?: 1.0
fun Long?.orOne(): Long = this ?: 1L
fun BigDecimal?.orOne(): BigDecimal = this ?: BigDecimal.ONE

fun Int.greaterThan(number: Int): Boolean = this > number

fun Boolean?.orFalse(): Boolean = this ?: false

fun Long?.isNull(): Boolean = this == null
fun Int?.isNull(): Boolean = this == null
fun Double?.isNull(): Boolean = this == null
fun BigDecimal?.isNull(): Boolean = this == null
fun Boolean?.isNull(): Boolean = this == null
fun Boolean?.isNotNull(): Boolean = this != null

fun Boolean.toFloat() = if (this) 1f else 0f