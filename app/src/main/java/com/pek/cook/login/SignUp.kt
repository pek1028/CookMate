package com.pek.cook.login

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pek.cook.R
import com.pek.cook.ui.theme.neu1
import com.pek.cook.ui.theme.neu3
import com.pek.cook.ui.theme.neu4
import com.pek.cook.ui.theme.neu5


@Composable
fun SignUp(onSignUpSuccess: () -> Unit,
           onSignUpFailed: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val isEmailValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(neu3),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.mipmap.cookmate),
            contentDescription = null,
            modifier = Modifier
                .width(240.dp)
                .aspectRatio(1f),
            Alignment.Center
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Sign Up",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            color = neu4
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Email", color = neu4) },
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.background(neu1),
            textStyle = TextStyle(neu4))
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password", color = neu4) },
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password = it },
            modifier = Modifier.background(neu1),
            textStyle = TextStyle(neu4))
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Confirm Password", color = neu4) },
            value = confirmPassword,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { confirmPassword = it },
            modifier = Modifier.background(neu1),
            textStyle = TextStyle(neu4))
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    if (password == confirmPassword) {
                        signUpWithEmailAndPassword(
                            email = email,
                            password = password,
                            onSignUpSuccess = onSignUpSuccess,
                            onSignUpFailed = onSignUpFailed
                        )
                    } else {
                        onSignUpFailed("Passwords do not match")
                    }
                },
                enabled = isEmailValid,
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = neu5)
            ) {
                Text(text = "Sign Up")
            }
        }
    }
}

private fun signUpWithEmailAndPassword(
    email: String,
    password: String,
    onSignUpSuccess: () -> Unit,
    onSignUpFailed: (String) -> Unit
) {
    Firebase.auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSignUpSuccess()
            } else {
                onSignUpFailed(task.exception?.message ?: "Unknown error")
            }
        }
}



@Preview(showBackground = true)
@Composable
fun SUPreview(){
    //SignUp()
}