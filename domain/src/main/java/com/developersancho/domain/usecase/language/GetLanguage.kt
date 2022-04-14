package com.developersancho.domain.usecase.language

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.usecase.ReturnUseCase
import com.developersancho.repository.langauge.LanguageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLanguage @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: LanguageRepository
) : ReturnUseCase<Unit, String>() {

    override suspend fun execute(params: Unit): Flow<String> {
        return repository.getLanguage
    }
}