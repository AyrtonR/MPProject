package com.example.MPProject.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterViewModel: ViewModel()  {

    fun registerAccount(email: String, password: String) {

        Firebase.auth.createUserWithEmailAndPassword(
            email, password
        )

    }
}