package com.pek.cook.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pek.cook.*
import com.pek.cook.R
import com.pek.cook.login.Login
import com.pek.cook.login.SignUp
import com.pek.cook.nav.BottomNavigationBar
import com.pek.cook.nav.NavRoutes
import com.pek.cook.ui.theme.*

private val auth by lazy{
    Firebase.auth
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun MainScreen(
) {
    val navController = rememberNavController()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "CookMate",
                            color = neu4
                        )
                    },
                    backgroundColor = neu5
                )
            },
            content = { NavigationHost(navController = navController) },
            bottomBar = { BottomNavigationBar(navController = navController) }
        )
}

@Composable
fun NavigationHost(
    navController: NavHostController
) {
    val recipeList = listOf<Recipe>()

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn()) NavRoutes.Home.route else NavRoutes.Login.route
    ) {

        composable(NavRoutes.Frame.route){
            MainScreen()
        }

        composable(NavRoutes.Home.route) {
            Home(navController)
        }

        composable(NavRoutes.Search.route) {
            Search(navController, recipeList)
        }

        composable(NavRoutes.MyRecipe.route) {
            MyRecipe(navController)
        }

        composable(NavRoutes.MyList.route){
            MyList(navController)
        }

        composable(NavRoutes.Update.route){
            Profile(auth ,navController)
        }

        composable(NavRoutes.Login.route){
            Login(navController)
        }

        composable(NavRoutes.SignUp.route){
            SignUp(navController)
        }

        composable(NavRoutes.Logout.route){
            auth.signOut()
            Login(navController)
        }

        composable("details/{recipeId}",
        arguments = listOf(navArgument("recipeId"){
            defaultValue = 0
            type = NavType.IntType
        })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0
            RecipeDetail(navController, recipeId)
        }

    }
}

@Composable
fun Home(navController : NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(neu3),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(modifier = Modifier.align(Alignment.TopCenter)) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier
                            .width(240.dp)
                            .aspectRatio(1f),
                        Alignment.Center
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Welcome Back",
                        color = neu4,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(onClick = { navController.navigate(NavRoutes.Update.route) },
                        modifier = Modifier
                            .height(50.dp)
                            .width(120.dp),
                        colors = ButtonDefaults.buttonColors(neu5)) {
                        Text(text = "Edit Profile")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        navController.navigate(NavRoutes.Logout.route)
                                     },
                        modifier = Modifier
                            .height(45.dp)
                            .width(120.dp),
                        colors = ButtonDefaults.buttonColors(neu5)) {
                        Text(text = "Log Out")
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(195.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(neu1)
                        ) {
                            Text(text = "\"Cooking is about passion, so it may look slightly temperamental in a way that it's too assertive to the naked eye.\" - Gordon Ramsay",
                                color = neu4,
                                fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center)
                        }
                    }
                    Spacer(modifier = Modifier.height(80.dp))
                    Text(text = "  Session Expired!\nPlease login again!")
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePreview(){
    Home(rememberNavController())
}