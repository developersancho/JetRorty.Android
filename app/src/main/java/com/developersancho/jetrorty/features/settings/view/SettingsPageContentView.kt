package com.developersancho.jetrorty.features.settings.view
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.Card
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.constraintlayout.compose.ConstraintLayout
//import androidx.constraintlayout.compose.Dimension
//import com.developersancho.framework.extension.appVersion
//import com.developersancho.jetrorty.features.settings.SettingsViewModel
//import com.developersancho.ui.resource.R
//import com.developersancho.ui.view.RortyDivider
//import com.developersancho.ui.view.ThemeSwitch
//import org.koin.androidx.compose.getViewModel
//
//@Composable
//fun SettingsPageContentView(
//    checkedState: MutableState<Boolean>,
//    viewModel: SettingsViewModel = getViewModel()
//) {
//    val context = LocalContext.current
//    val version = context.appVersion()
//
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.TopStart
//    ) {
//        Card(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//                .wrapContentHeight()
//        ) {
//            ConstraintLayout(
//                modifier = Modifier
//                    .padding(16.dp)
//            ) {
//                val (viewDivider, lblThemeMode, switchThemeMode,
//                    lblAppVersion, tvAppVersion) = createRefs()
//
//                Text(
//                    text = stringResource(id = R.string.text_theme_mode),
//                    style = MaterialTheme.typography.body2,
//                    modifier = Modifier.constrainAs(lblThemeMode) {
//                        top.linkTo(parent.top)
//                        start.linkTo(parent.start)
//                    })
//
//                AndroidView(
//                    factory = { context ->
//                        ThemeSwitch(context).apply {
//                            isChecked = checkedState.value
//                            setOnCheckedChangeListener { _, isChecked ->
//                                checkedState.value = isChecked
//                                viewModel.saveThemeMode(isChecked)
//                            }
//                        }
//                    },
//                    modifier = Modifier.constrainAs(switchThemeMode) {
//                        top.linkTo(lblThemeMode.top)
//                        bottom.linkTo(lblThemeMode.bottom)
//                        end.linkTo(parent.end)
//                    }
//                )
//
//                RortyDivider(modifier = Modifier
//                    .padding(top = 12.dp, bottom = 12.dp)
//                    .constrainAs(viewDivider) {
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                        top.linkTo(lblThemeMode.bottom)
//                        height = Dimension.fillToConstraints
//                    }
//                )
//
//                Text(
//                    text = stringResource(id = R.string.text_app_version),
//                    style = MaterialTheme.typography.body2,
//                    modifier = Modifier.constrainAs(lblAppVersion) {
//                        top.linkTo(viewDivider.bottom)
//                        bottom.linkTo(parent.bottom)
//                        start.linkTo(parent.start)
//                    })
//
//                Text(
//                    text = version,
//                    style = MaterialTheme.typography.subtitle1,
//                    modifier = Modifier.constrainAs(tvAppVersion) {
//                        top.linkTo(lblAppVersion.top)
//                        bottom.linkTo(lblAppVersion.bottom)
//                        end.linkTo(parent.end)
//                    })
//
//            }
//        }
//    }
//
//}