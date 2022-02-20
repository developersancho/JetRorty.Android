package com.developersancho.jetrorty.features.detail
//
//import android.content.res.Configuration
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.material.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.tooling.preview.Preview
//import com.developersancho.framework.base.BaseViewState
//import com.developersancho.framework.extension.cast
//import com.developersancho.jetrorty.features.detail.view.DetailPageContentView
//import com.developersancho.ui.resource.R
//import com.developersancho.ui.theme.JetRortyTheme
//import com.developersancho.ui.view.EmptyView
//import com.developersancho.ui.view.ErrorView
//import com.developersancho.ui.view.LoadingView
//import com.developersancho.ui.view.RortyToolbarWithNavIcon
//import com.ramcosta.composedestinations.annotation.Destination
//import com.ramcosta.composedestinations.navigation.DestinationsNavigator
//import org.koin.androidx.compose.getViewModel
//
//@Destination
//@Composable
//fun DetailScreen(
//    id: Int = 0,
//    viewModel: DetailViewModel = getViewModel(),
//    navigator: DestinationsNavigator
//) {
//    val uiState by viewModel.uiState.collectAsState()
//
//    DetailPage(
//        pressOnBack = {
//            navigator.navigateUp()
//        },
//        pageContent = {
//            when (uiState) {
//                is BaseViewState.Data -> DetailPageContentView(
//                    data = uiState.cast<BaseViewState.Data<DetailViewState>>().value
//                )
//                is BaseViewState.Empty -> EmptyView()
//                is BaseViewState.Error -> ErrorView(
//                    e = uiState.cast<BaseViewState.Error>().throwable,
//                    action = {
//                        viewModel.onTriggerEvent(DetailEvent.LoadDetail(id))
//                    })
//                is BaseViewState.Loading -> LoadingView()
//            }
//        })
//
//    LaunchedEffect(key1 = viewModel, block = {
//        viewModel.onTriggerEvent(DetailEvent.LoadDetail(id))
//    })
//}
//
//@Composable
//private fun DetailPage(
//    pressOnBack: () -> Unit = {},
//    pageContent: @Composable (PaddingValues) -> Unit
//) {
//    Scaffold(
//        topBar = {
//            RortyToolbarWithNavIcon(
//                R.string.toolbar_detail_title,
//                pressOnBack = pressOnBack
//            )
//        },
//        content = { pageContent.invoke(it) }
//    )
//}
//
//@Preview(showBackground = true, name = "Light Mode")
//@Preview(
//    showBackground = true, name = "Dark Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//)
//@Composable
//fun DetailScreenPreview() {
//    JetRortyTheme {
//        DetailPage {}
//    }
//}