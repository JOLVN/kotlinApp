package fr.jolan.jolanapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun LoginPage() {

    if(Firebase.auth.currentUser != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val user = Firebase.auth.currentUser
            
            Text(text = "You are logged in with the email ${user!!.email.toString()}", color = Color.White, textAlign = TextAlign.Center)


            OutlinedButton(
                modifier = Modifier.padding(10.dp),
                onClick = {
                Firebase.auth.signOut()

            }) {
                Text(text = "Log out", color = colorResource(R.color.text_color))
            }
        }
    }

    else {
        LoginForm()
    }

}

@Composable
fun LoginForm() {

    var email = remember { mutableStateOf("") }
    var pw = remember { mutableStateOf("") }
    var info = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(text = "You are not logged in", color = Color.White)
        MyOutlineTextField(text = email, label = "Email", isPw = false)
        MyOutlineTextField(text = pw, label = "Password", isPw = true)

        OutlinedButton(
            onClick = { login(email = email.value, pw = pw.value, info = info)},
            modifier = Modifier
                .padding(20.dp)
                .width(
                    100.dp
                )
        ) {
            Text(text = "Login")
        }
        Text(text = info.value, color = Color.White, textAlign = TextAlign.Center)
    }
}


fun login(email:String, pw:String, info: MutableState<String>){
    Firebase.auth
        .signInWithEmailAndPassword(email, pw)
        .addOnSuccessListener {
            info.value = "You are logged in with account ${it.user!!.email.toString()}"
        }
}


@Composable
fun MyOutlineTextField(text: MutableState<String>, label: String, isPw: Boolean) {
    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text(label) },
        visualTransformation =
        if(isPw)
            PasswordVisualTransformation()
        else
            VisualTransformation.None
    )
}
