package com.developersancho.domain.usecase.language

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.usecase.LocalUseCase
import com.developersancho.repository.langauge.LanguageRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class SaveLanguage @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: LanguageRepository
) : LocalUseCase<SaveLanguage.Params, Unit>() {
    data class Params(
        val language: String
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        repository.setLanguage(params.language)
        emit(Unit)
    }
}