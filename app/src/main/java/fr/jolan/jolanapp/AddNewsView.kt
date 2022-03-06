package fr.jolan.jolanapp

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore

@Composable
fun AddNewsView() {
    var nameState : String = "Guest"

    if (Firebase.auth.currentUser != null) {
        nameState = Firebase.auth.currentUser!!.email.toString()
    }

    var message = remember { mutableStateOf("") }

    val newsVM: NewsViewModel = viewModel(LocalContext.current as ComponentActivity)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background))
            .padding(40.dp)
    ) {

        Column() {
            Text(text = "Add news", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))

            MyInputField(message, "Message")

            OutlinedButton(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .border(1.dp, Color.Black),
                onClick = { addNewsButton(nameState, message, newsVM) }
            ) {
                Text(text = "Add news", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = colorResource(R.color.text_color))
            }
        }

    }
}

fun addNewsButton(
    nameState: String,
    msgState: MutableState<String>,
    newsVM: NewsViewModel
){

    val db = com.google.firebase.ktx.Firebase.firestore

    val newNews = News(nameState, msgState.value)

    db
        .collection("news")
        .add(newNews)

    msgState.value = ""
}

@Composable
fun MyInputField(state: MutableState<String>, label: String) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(vertical = 15.dp),
        value = state.value,
        onValueChange = { t ->
            state.value = t
        },

        label = { Text(text = label, color = Color.White) }
    )
}