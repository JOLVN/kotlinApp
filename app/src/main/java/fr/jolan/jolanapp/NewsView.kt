package fr.jolan.jolanapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.firestore.ktx.firestore

@Composable
fun NewsView() {
    val newsVM : NewsViewModel = viewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background))
            .padding(40.dp)
    ) {
        Column() {
            Text(text = "News", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            newsVM.news.value.forEach {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Text(text = it, color = Color.White)
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }

}