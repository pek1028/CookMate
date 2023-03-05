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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pek.cook.R
import com.pek.cook.model.AuthViewModel
import com.pek.cook.ui.theme.neu1
import com.pek.cook.ui.theme.neu3
import com.pek.cook.ui.theme.neu4
import com.pek.cook.ui.theme.neu5


@Composable
fun SignUp(
    navController : NavController
) {

    val context = LocalContext.current

    val viewModel = remember { AuthViewModel() }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
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
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            modifier = Modifier.background(neu1),
            textStyle = TextStyle(neu4))
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password", color = neu4) },
            value = viewModel.password,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { viewModel.password = it },
            modifier = Modifier.background(neu1),
            textStyle = TextStyle(neu4))
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Confirm Password", color = neu4) },
            value = viewModel.cpassword,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { viewModel.cpassword = it },
            modifier = Modifier.background(neu1),
            textStyle = TextStyle(neu4))
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    if (viewModel.password == viewModel.cpassword) {
                        viewModel.signUpUser(context, navController)
                    }
                },
                enabled = isValidEmail(viewModel.email.text),
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



@Preview(showBackground = true)
@Composable
fun SUPreview(){
    //SignUp()
}