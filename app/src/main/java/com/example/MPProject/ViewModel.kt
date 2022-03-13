package com.example.MPProject

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.concurrent.schedule

class ViewModel: ViewModel() {
    var username = mutableStateOf("")
    var loginError = mutableStateOf("")

    fun loginUser( email: String, pw: String ){
        if (email.isEmpty() || pw.isEmpty())
        {
            setError("Email or password cannot be empty.")
        }
        Firebase.auth
            .signInWithEmailAndPassword(email, pw)
            .addOnSuccessListener {
                username.value = email
            }
            .addOnFailureListener{
                setError("Incorrect Email or Password")
            }
    }
    fun logout(){
        Firebase.auth.signOut()
        username.value = ""
    }

    private fun setError(error: String)
    {
        loginError.value = error
        Timer().schedule(5000){
            loginError.value = ""
        }
    }


}