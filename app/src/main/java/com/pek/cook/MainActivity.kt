package com.pek.cook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pek.cook.home.NavigationHost
import com.pek.cook.ui.theme.COOKTheme
import com.pek.cook.ui.theme.neu3

class MainActivity : ComponentActivity() {

    companion object{
        val TAG: String = MainActivity::class.java.simpleName
    }

    private val auth by lazy{
        Firebase.auth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            COOKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = neu3
                ) {
                    NavigationHost(navController = rememberNavController())
                }
            }
        }
    }
}

