package com.pek.cook.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.pek.cook.R
import com.pek.cook.model.AuthViewModel
import com.pek.cook.ui.theme.neu1
import com.pek.cook.ui.theme.neu3
import com.pek.cook.ui.theme.neu4
import com.pek.cook.ui.theme.neu5

@Composable
fun Profile(
    auth: FirebaseAuth,
    navController : NavController
){
    val context = LocalContext.current
    val currentUser = auth.currentUser
    var email by remember { mutableStateOf(currentUser?.email ?: "") }
    var password by remember { mutableStateOf("") }
    var cpassword by remember { mutableStateOf("") }
    val viewModel = remember { AuthViewModel() }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    Box(
        modifier = Modifier
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
                    verticalArrangement = Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        painter = painterResource(R.mipmap.cookmate),
                        contentDescription = null,
                        modifier = Modifier
                            .width(240.dp)
                            .aspectRatio(1f),
                        alignment = Alignment.Center
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Change your information here:",
                        fontSize = 20.sp, color = neu5
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    TextField(
                        label = { Text(text = "Email", color = neu4) },
                        singleLine = true,
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.background(neu1),
                        textStyle = TextStyle(neu4),
                        readOnly = true
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        label = { Text(text = "Password", color = neu4) },
                        singleLine = true,
                        value = password,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        onValueChange = { password = it },
                        modifier = Modifier.background(neu1),
                        textStyle = TextStyle(neu4)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        label = { Text(text = "Confirm Password", color = neu4) },
                        singleLine = true,
                        value = cpassword,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        onValueChange = { cpassword = it },
                        modifier = Modifier.background(neu1),
                        textStyle = TextStyle(neu4)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        if (password == cpassword) {

                            // Call update password function in AuthViewModel
                            viewModel.updatePassword(context, password)
                        }else {
                            // Show an error message if the passwords don't match
                            Toast.makeText(context, "Passwords don't match", Toast.LENGTH_SHORT).show()
                        }
                                     },
                        colors = ButtonDefaults.buttonColors(backgroundColor = neu5),
                        shape = RoundedCornerShape(45.dp),
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp),
                        enabled = isValidEmail(email)
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}