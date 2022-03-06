package fr.jolan.jolanapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.jolan.jolanapp.ui.theme.JolanAppTheme





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            JolanAppTheme {

                val navControl = rememberNavController()
                val title = "Home"

                Scaffold(
                    topBar = { MyTopBar2(navControl) },
                    bottomBar = { BottomNavBar2(navControl) },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(R.color.background))
                ) {
                    NavHost(navController = navControl, startDestination = "home") {
                        composable("home") { HomeView() }
                        composable("add_news") { AddNewsView() }
                        composable("news") { NewsView() }
                    }
                }
                
            }
        }


    }
}

@Composable
fun MyMainView() {


}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JolanAppTheme {
        MyMainView()
    }
}