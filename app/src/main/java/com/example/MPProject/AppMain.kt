package com.example.MPProject

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.MPProject.viewmodel.LoginViewModel

const val HOME_ROUTE = "home"
const val LOGIN_ROUTE = "login"
const val REGISTER_ROUTE = "Register"
const val ITEM_ROUTE = "item"


@Composable
fun MainView() {
    val loginViewModel = viewModel<LoginViewModel>()
    val dataViewModel= viewModel<DataViewModel>()
    val navController = rememberNavController()
        Scaffold(
            topBar = {TopBar(navController, LoginViewModel())},
            bottomBar = {BottomBar(navController)},
            content = { MainContentView(navController, dataViewModel, loginViewModel) }
        )
}

@Composable
fun MainContentView(navController: NavHostController, dataViewModel: DataViewModel,loginViewModel : LoginViewModel) {
    val loginVM = viewModel<LoginViewModel>()
    NavHost(navController = navController, startDestination = HOME_ROUTE){
        composable(route = HOME_ROUTE){ HomePage()}
        composable(route = LOGIN_ROUTE){Login(loginViewModel, navController)}
        composable(route = REGISTER_ROUTE){CreateAccount(navController)}
        composable( route = ITEM_ROUTE){ DataEntryView(dataViewModel, loginViewModel) }
    }
}

@Composable
fun TopBar(navController: NavHostController, loginVM: LoginViewModel) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color(color = 0xFF52988B))
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
    ){
        if (loginVM.username.value.isEmpty()){
            Text(text = "")
            OutlinedButton(onClick = {navController.navigate(LOGIN_ROUTE)}){
                Text(text = "Login")
            }
        } else if (loginVM.username.value == "null") {
            Text(text = "")
            OutlinedButton(onClick = {navController.navigate(LOGIN_ROUTE)}){
                Text(text = "Login")
            }
        } else {
            Text(text = loginVM.username.value)
            OutlinedButton(onClick = {
                loginVM.logout()
                navController.navigate(HOME_ROUTE) }){
                Text(text = "Logout")
            }
        }
    }
}

@Composable
fun DataEntryView(dataViewModel: DataViewModel , loginViewModel: LoginViewModel) {
    var entryValue by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        OutlinedTextField(
            value = entryValue ,
            onValueChange = { entryValue = it },
            label = { Text(text = "Item name") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(
            onClick = { dataViewModel.addShoppingListItem(loginViewModel.username.value, entryValue); entryValue = "" }
        ) {
            Text(text = "Add item to shopping list")
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (dataViewModel.itemSuccess.value.isNotEmpty())
        {
            Text(text = dataViewModel.itemSuccess.value, color = Color.Green)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(thickness = 2.dp)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color(color = 0xFF52988B)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        BottomIcon(navController = navController, route = HOME_ROUTE, PainterId = R.drawable.ic_home)
        BottomIcon(navController = navController, route = ITEM_ROUTE, PainterId = R.drawable.ic_public)
    }
}

@Composable
fun BottomIcon(navController: NavHostController, route: String, PainterId: Int) {
    Card( modifier = Modifier
        .clickable { navController.navigate(route) }
        .width(100.dp), backgroundColor = Color.Transparent, elevation = 0.dp) {
        Icon(painter = painterResource(id = PainterId), contentDescription ="NavigationIcon", tint = Color.Unspecified)
    }
}

