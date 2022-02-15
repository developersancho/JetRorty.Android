package com.developersancho.testing

import android.annotation.SuppressLint
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.json.JSONException
import org.json.JSONObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner
import java.lang.reflect.Constructor

@RunWith(BlockJUnit4ClassRunner::class)
abstract class BaseModelTest: BaseMockTest() {
    private val parameters = ArrayList<String>()

    abstract fun checkModelClass(): Class<*>

    abstract fun checkModelFields(): List<String>

    protected val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private inline fun <reified T> T.toJson(): String {
        return try {
            val jsonAdapter = moshi.adapter(T::class.java).serializeNulls().lenient()
            jsonAdapter.toJson(this)
        } catch (ex: Exception) {
            ""
        }
    }

    @SuppressLint("NewApi")
    private fun getConstructor(clazz: Class<*>): Any? {
        val constructors = clazz.declaredConstructors
        if (constructors.isNotEmpty()) {
            val nonConstructor = findNonConstructor(constructors)
            nonConstructor.isAccessible = true
            return try {
                nonConstructor.newInstance(*arrayOfNulls(nonConstructor.parameters.size))
            } catch (ex: Exception) {
                ex.printStackTrace()
                null
            }
        }

        return null
    }

    private fun findNonConstructor(constructors: Array<Constructor<*>>): Constructor<*> {
        var originalConstructor = constructors.first()
        for (element in constructors) {
            if (!element.isSynthetic) {
                originalConstructor = element
            }
        }
        return originalConstructor
    }

/*    @SuppressLint("NewApi")
    @Before
    @Throws(JSONException::class)
    fun setUp() {

    }*/
    @SuppressLint("NewApi")
    override fun onCreate() {
        super.onCreate()
        val modelClass = checkModelClass()
        val obj = getConstructor(modelClass)
        val responseJson = obj.toJson()
        val jsonObject = JSONObject(responseJson)

        val iterator = jsonObject.keys()
        parameters.clear()
        iterator.forEachRemaining { parameters.add(it) }
    }

    @Test
    fun validateModelFields() {
        val list = checkModelFields()
        val paramItems = Matchers.`is`(Matchers.`in`(parameters))
        val listItems = Matchers.`is`(Matchers.`in`(list))
        MatcherAssert.assertThat(list, CoreMatchers.everyItem(paramItems))
        MatcherAssert.assertThat(parameters, CoreMatchers.everyItem(listItems))
    }
}
