package com.pek.cook.login


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
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.NavController
import com.pek.cook.nav.NavRoutes
import com.pek.cook.R
import com.pek.cook.model.AuthViewModel
import com.pek.cook.ui.theme.*

@Composable
fun Login(
    navController : NavController
){
    val context = LocalContext.current
    val viewModel = remember { AuthViewModel() }

    val email by remember {
        mutableStateOf("")
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
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
                text = "A recipe app for\n-DISCOVERY-",
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
                value = viewModel.email,
                onValueChange = { viewModel.email = it },
                modifier = Modifier.background(neu1),
                textStyle = TextStyle(neu4))
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "Password", color = neu4) },
                singleLine = true,
                value = viewModel.password,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { viewModel.password = it },
                modifier = Modifier.background(neu1),
                textStyle = TextStyle(neu4)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(onClick = {
                    viewModel.loginUser(context, navController)
                },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = neu5),
                    enabled = isValidEmail(viewModel.email.text)
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
