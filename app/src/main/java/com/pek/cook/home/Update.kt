package com.pek.cook.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.pek.cook.R
import com.pek.cook.ui.theme.neu1
import com.pek.cook.ui.theme.neu3
import com.pek.cook.ui.theme.neu4
import com.pek.cook.ui.theme.neu5

@Composable
fun Profile(
    auth: FirebaseAuth,
    onPasswordChangeSuccess: () -> Unit,
    onPasswordChangeFailed: (String) -> Unit,
    navController : NavController
){
    val currentUser = auth.currentUser
    var email by remember { mutableStateOf(currentUser?.email ?: "") }
    var password by remember { mutableStateOf("") }
    var cPassword by remember { mutableStateOf("") }

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
                        value = cPassword,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        onValueChange = { cPassword = it },
                        modifier = Modifier.background(neu1),
                        textStyle = TextStyle(neu4)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(onClick = { val user = auth.currentUser
                        if (user != null) {
                            val credential = EmailAuthProvider.getCredential(user.email!!, password)
                            user.reauthenticate(credential)
                                .addOnSuccessListener {
                                    if (password == cPassword) {
                                        user.updatePassword(cPassword.toString())
                                            .addOnSuccessListener {
                                                onPasswordChangeSuccess()
                                            }
                                            .addOnFailureListener {
                                                onPasswordChangeFailed(it.message!!)
                                            }
                                    } else {
                                        onPasswordChangeFailed("Passwords do not match.")
                                    }
                                }
                                .addOnFailureListener {
                                    onPasswordChangeFailed(it.message!!)
                                }
                        } },
                        colors = ButtonDefaults.buttonColors(backgroundColor = neu5),
                        shape = RoundedCornerShape(50.dp)
                    ) {

                    }
                }
            }
        }
    }
}