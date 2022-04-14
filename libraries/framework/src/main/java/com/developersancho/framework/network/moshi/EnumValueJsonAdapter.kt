package com.developersancho.framework.network.moshi

import com.squareup.moshi.*
import java.io.IOException
import kotlin.reflect.KClass

class EnumValueJsonAdapter<T : IValueEnum>
private constructor(
    private val enumType: Class<T>,
    private val fallbackValue: T?,
    private val useFallbackValue: Boolean
) : JsonAdapter<T>() {
    private val constants: Array<T> = enumType.enumConstants as Array<T>
    private val enumValues: HashMap<String, T> = constants.associateByTo(hashMapOf()) { it.value }

    /**
     * Create a new adapter for this enum with a fallback value to use when the JSON value does not
     * match any of the enum's constants. Note that this value will not be used when the JSON value is
     * null, absent, or not a string. Also, the string values are case-sensitive, and this fallback
     * value will be used even on case mismatches.
     */
    fun withUnknownFallback(fallbackValue: T?): EnumValueJsonAdapter<T> =
        EnumValueJsonAdapter(enumType, fallbackValue, true)

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): T? {
        val value = reader.nextInt()
        if (enumValues.containsKey(value.toString())) {
            return enumValues[value.toString()]
        }

        val path = reader.path
        if (!useFallbackValue) {
            throw JsonDataException("Unknown value of enum ${enumType.name} ($value) at path $path")
        }
        return fallbackValue
    }

    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: T?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.value(value.value)
    }

    override fun toString() = "EnumJsonAdapter(" + enumType.name + ")"

    companion object {
        fun <T : IValueEnum> create(
            enumType: Class<T>,
            unknownFallback: T? = null
        ): EnumValueJsonAdapter<T> {
            val useFallbackValue = (unknownFallback != null)
            return EnumValueJsonAdapter(enumType, unknownFallback, useFallbackValue)
        }
    }
}

interface IValueEnum {
    val value: String
}

fun <T : IValueEnum> Moshi.Builder.addValueEnum(
    kClass: KClass<T>,
    unknownFallback: T? = null
): Moshi.Builder =
    this.add(kClass.java, EnumValueJsonAdapter.create(kClass.java, unknownFallback).nullSafe())