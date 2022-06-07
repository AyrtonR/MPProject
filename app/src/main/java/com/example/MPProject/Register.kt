package com.example.MPProject

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.MPProject.viewmodel.LoginViewModel
import com.example.MPProject.viewmodel.RegisterViewModel

@Composable
fun CreateAccount(NavController : NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val registerVM: RegisterViewModel = viewModel()
        var emailValue by remember { mutableStateOf("") }
        var pwValue by remember { mutableStateOf("") }

        OutlinedTextField(
            label = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            value = emailValue,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                emailValue = it
            })
        OutlinedTextField(
            label = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            value = pwValue,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                pwValue = it
            })

        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { registerVM.registerAccount(emailValue, pwValue); NavController.navigate(HOME_ROUTE)
            }) { Text(text = "Create an account") }
    }}