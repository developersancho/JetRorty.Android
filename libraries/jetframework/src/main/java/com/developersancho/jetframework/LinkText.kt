package com.developersancho.jetframework

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

data class LinkTextData(
    val text: String,
    val tag: String? = null,
    val annotation: String? = null,
    val onClick: ((str: AnnotatedString.Range<String>) -> Unit)? = null,
)

@Composable
fun LinkText(
    linkTextData: List<LinkTextData>,
    modifier: Modifier = Modifier,
) {
    val annotatedString = createAnnotatedString(linkTextData)

    ClickableText(
        text = annotatedString,
        style = MaterialTheme.typography.body1,
        onClick = { offset ->
            linkTextData.forEach { annotatedStringData ->
                if (annotatedStringData.tag != null && annotatedStringData.annotation != null) {
                    annotatedString.getStringAnnotations(
                        tag = annotatedStringData.tag,
                        start = offset,
                        end = offset,
                    ).firstOrNull()?.let {
                        annotatedStringData.onClick?.invoke(it)
                    }
                }
            }
        },
        modifier = modifier,
    )
}

@Composable
private fun createAnnotatedString(data: List<LinkTextData>): AnnotatedString {
    return buildAnnotatedString {
        data.forEach { linkTextData ->
            if (linkTextData.tag != null && linkTextData.annotation != null) {
                pushStringAnnotation(
                    tag = linkTextData.tag,
                    annotation = linkTextData.annotation,
                )
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary,
                        textDecoration = TextDecoration.Underline,
                    ),
                ) {
                    append(linkTextData.text)
                }
                pop()
            } else {
                append(linkTextData.text)
            }
        }
    }
}

// ********** USAGE *******************
//            LinkText(
//                linkTextData = listOf(
//                    LinkTextData(
//                        text = "Icons made by ",
//                    ),
//                    LinkTextData(
//                        text = "smalllikeart",
//                        tag = "icon_1_author",
//                        annotation = "https://www.flaticon.com/authors/smalllikeart",
//                        onClick = {
//                            Log.d("Link text", "${it.tag} ${it.item}")
//                        },
//                    ),
//                    LinkTextData(
//                        text = " from ",
//                    ),
//                    LinkTextData(
//                        text = "Flaticon",
//                        tag = "icon_1_source",
//                        annotation = "https://www.flaticon.com/",
//                        onClick = {
//                            Log.d("Link text", "${it.tag} ${it.item}")
//                        },
//                    )
//                ),
//                modifier = Modifier
//                    .padding(
//                        all = 16.dp,
//                    ),
//            )