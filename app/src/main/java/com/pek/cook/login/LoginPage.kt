package com.pek.cook.login


import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pek.cook.nav.NavRoutes
import com.pek.cook.R
import com.pek.cook.setIsLoggedIn
import com.pek.cook.ui.theme.*

@Composable
fun Login(
    navController : NavController,
    onLoginSuccess: () -> Unit,
    onLoginFailed: (String) -> Unit
){
    val firebaseAuth = Firebase.auth
    val focusManager = LocalFocusManager.current
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    val isEmailValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    Box(
        modifier = Modifier
            .background(neu3)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(270.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.mipmap.cookmate),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                Alignment.Center
            )
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(8.dp)
                    .width(210.dp),
                color = neu5,
                textAlign = TextAlign.Center
            )
            Text(
                text = "-A recipe app for meal planning and discovery-",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .width(210.dp),
                color = d_gray,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            TextField(
                label = { Text(text = "Email", color = neu4) },
                singleLine = true,
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.background(neu1),
                textStyle = TextStyle(neu4))
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
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(onClick = {
                    loginWithEmailAndPassword(email, password,
                    onLoginSuccess = onLoginSuccess,
                    onLoginFailed = onLoginFailed)
                    navController.navigate(NavRoutes.Frame.route)
                },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = neu5),
                    enabled = isEmailValid
                ) {
                    Text(text = "LOGIN")
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
            ClickableText(
                text = AnnotatedString("No Account?\n\nSign Up Now"),
                onClick = { navController.navigate(NavRoutes.SignUp.route) },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default
                )
            )
        }
    }
}

private fun loginWithEmailAndPassword(
    email: String,
    password: String,
    onLoginSuccess: () -> Unit,
    onLoginFailed: (String) -> Unit
) {
    Firebase.auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener{ task ->
            if(task.isSuccessful){
                setIsLoggedIn(true) // set isLoggedIn flag to true
                onLoginSuccess()
            }else{
                setIsLoggedIn(false)
                onLoginFailed(task.exception?.message?:"Error")
            }
        }
}