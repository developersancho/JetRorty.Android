package com.developersancho.jetrorty.features.favorites
//
//import com.developersancho.domain.usecase.favorite.DeleteFavorite
//import com.developersancho.domain.usecase.favorite.GetFavorites
//import com.developersancho.framework.base.BaseViewState
//import com.developersancho.framework.base.mvi.MviViewModel
//
//class FavoritesViewModel(
//    private val getFavorites: GetFavorites,
//    private val deleteFavorite: DeleteFavorite
//) : MviViewModel<BaseViewState<FavoritesViewState>, FavoritesEvent>() {
//
//    override fun onTriggerEvent(eventType: FavoritesEvent) {
//        when (eventType) {
//            is FavoritesEvent.DeleteItem -> onDeleteItem(eventType.id)
//            is FavoritesEvent.LoadFavorite -> onLoadFavorite()
//        }
//    }
//
//    private fun onLoadFavorite() = safeLaunch {
//        call(getFavorites(Unit)) {
//            if (it.isEmpty()) {
//                setState(BaseViewState.Empty)
//            } else {
//                setState(BaseViewState.Data(FavoritesViewState(it)))
//            }
//        }
//    }
//
//    private fun onDeleteItem(id: Int) = safeLaunch {
//        call(deleteFavorite(DeleteFavorite.Params(id))) {
//            onTriggerEvent(FavoritesEvent.LoadFavorite)
//        }
//    }
//}