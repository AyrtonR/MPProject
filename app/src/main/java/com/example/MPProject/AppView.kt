package com.example.MPProject

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


const val HOME_ROUTE = "Home"
const val NEWS_ROUTE = "News"

@Composable
fun MainView() {
    val userVM = viewModel<ViewModel>()

    if(userVM.username.value.isEmpty()){
        LoginView(userVM)
    }else {
        MainScaffoldView()
    }
}

@Composable
fun MainScaffoldView() {

    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBarView() },
        bottomBar = { BottomBarView(navController) },
        content = { MainContentView(navController) }
    )
}

@Composable
fun MainContentView(navController: NavHostController) {
    val NewsViewM = viewModel<NewsModel>()

    NavHost(navController = navController, startDestination = HOME_ROUTE ){
        composable( route = HOME_ROUTE ){ HomeView() }
        composable( route = NEWS_ROUTE){ NewsView(NewsViewM) }
    }
}

@Composable
fun HomeView() {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFFFFFF))) {
        Spacer(modifier = Modifier.fillMaxHeight(0.07f))
        Text(fontSize = 5.em, textAlign = TextAlign.Justify, text = "Welcome to the freshest News Site in the World!\n" +
                "Click on the icon below to access the News Tab!")
    }
}

@Composable
fun NewsView(NewsViewM: NewsModel) {

    var newsText by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF7F7F7))
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = newsText ,
            onValueChange = { newsText = it },
            label = { Text(text = "Latest News") })

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = { NewsViewM.addNews( News(newsText) ) }
        ) {
            Text(text = "Add News")
        }

        Spacer(modifier = Modifier.height(10.dp))

        NewsViewM.news.value.forEach {
            Divider(thickness = 4.dp)
            Text(text = it.message)
        }
        Divider(thickness = 4.dp)
    }
}

@Composable
fun BottomBarView(navController: NavHostController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xBC062BE4)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = "Home",
            modifier = Modifier.clickable {  navController.navigate(HOME_ROUTE)  })
        Icon(
            painter = painterResource(id = R.drawable.ic_public),
            contentDescription = "News",
            modifier = Modifier.clickable {  navController.navigate(NEWS_ROUTE)  })
    }
}

@Composable
fun TopBarView() {
    val userVM = viewModel<ViewModel>()

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xA40338F7))
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = userVM.username.value)
        OutlinedButton(onClick = { userVM.logout() }) {
            Text(text = "LogOut")
        }
    }
}


@Composable
fun LoginView(userVM: ViewModel) {
    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email , 
            onValueChange = { email = it },
            label = { Text(text = "E-mail") })
        OutlinedTextField(
            value = pw ,
            onValueChange = { pw = it },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation())
        OutlinedButton(onClick = { userVM.loginUser(email,pw) }) {
            Text(text = "Log In")
        }
    }
}