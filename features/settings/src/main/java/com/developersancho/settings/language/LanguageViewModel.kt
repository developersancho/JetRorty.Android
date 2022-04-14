package com.developersancho.settings.language

import androidx.compose.runtime.mutableStateOf
import com.developersancho.framework.base.mvvm.MvvmViewModel
import com.developersancho.model.dto.language.LanguageDto
import com.developersancho.provider.LanguageProvider
import com.developersancho.provider.ResourceProvider
import com.developersancho.theme.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val languageProvider: LanguageProvider,
    private val resourceProvider: ResourceProvider
) : MvvmViewModel() {

    private var languages = emptyList<LanguageDto>()

    val langs = mutableStateOf<List<LanguageDto>>(emptyList())

    init {
        languages = getLanguages()
        getLanguage()
    }

    fun saveLanguageCode(code: String) {
        languageProvider.saveLanguageCode(code)
    }

    private fun getLanguageCode(): String {
        return languageProvider.getLanguageCode()
    }

    private fun getLanguage() {
        languages.map {
            it.isSelected = it.code == getLanguageCode()
        }
        langs.value = languages
    }

    private fun getLanguages(): List<LanguageDto> {
        return listOf(
            LanguageDto(
                id = 1,
                code = "en",
                name = resourceProvider.getString(R.string.text_language_english),
                isSelected = false
            ),
            LanguageDto(
                id = 2,
                code = "tr",
                name = resourceProvider.getString(R.string.text_language_turkish),
                isSelected = false
            )
        )
    }
}