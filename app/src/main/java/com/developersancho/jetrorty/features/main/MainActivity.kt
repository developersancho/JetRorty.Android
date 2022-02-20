package com.developersancho.jetrorty.features.main

//import android.os.Bundle
//import androidx.activity.compose.setContent
//import androidx.appcompat.app.AppCompatActivity
//import com.developersancho.framework.extension.toast
//import org.koin.androidx.viewmodel.ext.android.viewModel
//
//class MainActivity : AppCompatActivity() {
//    private val viewModel by viewModel<MainViewModel>()
//
//    private var backPressed = 0L
//
//    private val finish: () -> Unit = {
//        if (backPressed + 3000 > System.currentTimeMillis()) {
//            finishAndRemoveTask()
//        } else {
//            toast(getString(com.developersancho.ui.resource.R.string.app_exit_label))
//        }
//        backPressed = System.currentTimeMillis()
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MainRoot(finish = finish)
//        }
//    }
//}