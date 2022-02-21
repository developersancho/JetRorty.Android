package com.developersancho.jetrorty.features.detail.view
//
//import android.content.res.Configuration
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.developersancho.model.dto.CharacterDto
//import com.developersancho.ui.theme.JetRortyTheme
//import com.developersancho.ui.view.CoverImage
//
//@Composable
//fun DetailHeaderView(character: CharacterDto?) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(250.dp)
//            .padding(top = 16.dp, bottom = 16.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        CoverImage(
//            data = "${character?.image}",
//            imageModifier = Modifier
//                .clip(RoundedCornerShape(size = 16.dp)),
//            elevation = 0.dp,
//            backgroundColor = MaterialTheme.colors.background
//        )
//    }
//}
//
//@Preview(
//    showBackground = true,
//    name = "Light Mode"
//)
//@Preview(
//    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    name = "Dark Mode"
//)
//@Composable
//fun DetailHeaderItemViewPreview() {
//    JetRortyTheme {
//        DetailHeaderView(CharacterDto.init())
//    }
//}