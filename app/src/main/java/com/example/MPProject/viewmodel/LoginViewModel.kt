package com.example.MPProject.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

open class LoginViewModel: ViewModel() {
    val username = mutableStateOf(Firebase.auth.currentUser?.email.toString())


    fun loginUser( email: String, pw: String){
        Firebase.auth
            .signInWithEmailAndPassword(email, pw)
            .addOnSuccessListener {
                username.value = email
            }
    }
    fun logout(){
        Firebase.auth.signOut()
        username.value = ""
    }
}