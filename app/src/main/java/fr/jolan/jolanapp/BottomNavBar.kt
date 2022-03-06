package fr.jolan.jolanapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun BottomNavBar2(navC: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0b0c0c))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_baseline_home_24),
            contentDescription = "home",
            Modifier.clickable { navC.navigate("home")},
            tint = Color.White,
        )
        Icon(
            painter = painterResource(R.drawable.ic_baseline_add_card_24),
            contentDescription = "add_news",
            Modifier.clickable { navC.navigate("add_news") },
            tint = Color.White
        )
        Icon(
            painter = painterResource(R.drawable.ic_baseline_credit_card_24),
            contentDescription = "news",
            Modifier.clickable { navC.navigate("news") },
            tint = Color.White
        )
    }
}