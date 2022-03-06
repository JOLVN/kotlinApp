package fr.jolan.jolanapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NewsViewModel: ViewModel() {
    var news = mutableStateOf(listOf<String>())
    val db = Firebase.firestore

    init {
        db
            .collection("news")
            .addSnapshotListener{ value, error ->
                if (error != null) {

                }
                else if (value != null && !value.isEmpty) {
                    val nws = mutableListOf<String>()
                    for(d in value.documents) {
                        nws.add(d.get("msg").toString())
                    }
                    news.value = nws
                }
            }
    }

}