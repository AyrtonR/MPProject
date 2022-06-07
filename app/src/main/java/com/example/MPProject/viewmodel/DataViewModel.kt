package com.example.MPProject

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.MPProject.viewmodel.LoginViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DataViewModel : ViewModel() {
    val db = Firebase.firestore
    val items = mutableStateMapOf<String, String>()
    var itemSuccess = mutableStateOf("");
    var dbErr = mutableStateOf("");

    fun getShoppingList( email: String ){
        db.collection("notes")
            .whereEqualTo("userid", email)
            .get()
            .addOnSuccessListener { result ->
                dbErr.value = ""
                items.clear()
                for (document in result) {
                    items[document.id] = document.data["value"].toString()
                }
            }
    }

    fun addShoppingListItem( email: String, itemValue: String){
        val data1 = hashMapOf(
            "value" to itemValue,
            "userid" to email
        )
        db.collection("items")
            .add(data1)
            .addOnFailureListener {
            }
        getShoppingList(email)
    }
    }

