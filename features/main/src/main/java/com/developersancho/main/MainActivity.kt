package com.developersancho.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.developersancho.framework.extension.toast
import com.developersancho.ui.resource.R

class MainActivity : AppCompatActivity() {

    private var backPressed = 0L

    private val finish: () -> Unit = {
        if (backPressed + 3000 > System.currentTimeMillis()) {
            finishAndRemoveTask()
        } else {
            toast(getString(R.string.app_exit_label))
        }
        backPressed = System.currentTimeMillis()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainRoot(finish = finish)
        }
    }
}