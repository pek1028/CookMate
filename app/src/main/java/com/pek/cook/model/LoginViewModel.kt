package com.pek.cook.model

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.pek.cook.nav.NavRoutes
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel : ViewModel() {
    var email by mutableStateOf(TextFieldValue())
    var password by mutableStateOf(TextFieldValue())

    fun loginUser(context: Context, navController: NavController) {
        val auth = FirebaseAuth.getInstance()
        viewModelScope.launch {
            try {
                val result = auth.signInWithEmailAndPassword(email.text, password.text).await()
                Toast.makeText(context, "Sign in successful", Toast.LENGTH_SHORT).show()
                navController.navigate(NavRoutes.Frame.route)
            } catch (e: Exception) {
                Toast.makeText(context, "Sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signUpUser(context: Context, navController: NavController) {
        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email.text, password.text)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign up success, update UI with the signed-in user's information
                    Toast.makeText(context, "Sign up successful!", Toast.LENGTH_SHORT).show()
                    navController.navigate("home")
                } else {
                    // If sign up fails, display a message to the user.
                    Toast.makeText(context, "Sign up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
