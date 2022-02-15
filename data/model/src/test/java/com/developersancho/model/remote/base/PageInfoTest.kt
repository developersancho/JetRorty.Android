package com.developersancho.model.remote.base

import com.developersancho.testing.BaseModelTest
import org.junit.Assert
import org.junit.Test

class PageInfoTest : BaseModelTest() {
    override fun checkModelClass(): Class<*> {
        return PageInfo::class.java
    }

    override fun checkModelFields(): List<String> {
        return listOf(
            "count",
            "next",
            "pages",
            "prev"
        )
    }

    @Test
    fun createResponse() {
        val pageInfo = PageInfo(
            count = 10,
            next = "",
            pages = 1,
            prev = ""
        )

        Assert.assertEquals(10, pageInfo.count)
        Assert.assertEquals(1, pageInfo.pages)
        Assert.assertEquals("", pageInfo.next)
        Assert.assertEquals("", pageInfo.prev)
    }
}