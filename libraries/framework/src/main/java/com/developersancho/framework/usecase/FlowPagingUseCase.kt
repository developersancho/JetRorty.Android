package com.developersancho.framework.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowPagingUseCase<in Params, ReturnType> where ReturnType : Any {

    protected abstract fun execute(params: Params): Flow<PagingData<ReturnType>>

    operator fun invoke(params: Params): Flow<PagingData<ReturnType>> = execute(params)
        .flowOn(Dispatchers.IO)
}
