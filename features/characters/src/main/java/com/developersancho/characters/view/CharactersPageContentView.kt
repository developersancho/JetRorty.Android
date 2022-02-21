package com.developersancho.characters.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.developersancho.characters.CharactersViewModel
import com.developersancho.characters.CharactersViewState
import com.developersancho.framework.compose.rememberFlowWithLifecycle
import com.developersancho.framework.extension.orZero
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersPageContentView(
    viewModel: CharactersViewModel = getViewModel(),
    paddingValues: PaddingValues,
    viewState: CharactersViewState,
    selectItem: (Int) -> Unit = {}
) {
    val pagingItems = rememberFlowWithLifecycle(viewState.pagedData).collectAsLazyPagingItems()

    SwipeRefresh(
        state = rememberSwipeRefreshState(
            isRefreshing = pagingItems.loadState.refresh == LoadState.Loading
        ),
        onRefresh = { pagingItems.refresh() },
        indicatorPadding = paddingValues,
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                scale = true
            )
        },
        content = {
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 4.dp),
            ) {
                items(pagingItems.itemCount) { index ->
                    pagingItems[index]?.let {
                        CharacterRow(viewModel, dto = it, onDetailClick = { selectItem.invoke(it.id.orZero()) })
                    }
                }

                if (pagingItems.loadState.append == LoadState.Loading) {
                    item {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        ) {
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    }
                }
            }
        }
    )
}