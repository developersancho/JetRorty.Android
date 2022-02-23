/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.framework.base.app

data class RemoteLog(
    var priority: String,
    var tag: String?,
    var message: String,
    var throwable: String?,
    val time: String
)