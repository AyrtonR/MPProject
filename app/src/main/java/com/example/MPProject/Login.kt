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
import androidx.navigation.NavHostController
import com.example.MPProject.viewmodel.LoginViewModel

@Composable
fun Login(loginVM: LoginViewModel, NavController : NavHostController) {
    var email by remember { mutableStateOf(value = "") }
    var pw by remember { mutableStateOf(value = "") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = email, onValueChange = { email = it }, label = { Text(text = "Email") },
            modifier = Modifier.width(300.dp),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = pw, onValueChange = { pw = it }, label = { Text(text = "Password") },
            modifier = Modifier.width(300.dp),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(onClick = {
            loginVM.loginUser(
                email,
                pw
            ); NavController.navigate(HOME_ROUTE)
        }) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(onClick = {
            NavController.navigate(REGISTER_ROUTE)
        }) {
            Text(text = "Register")
        }
    }
}
