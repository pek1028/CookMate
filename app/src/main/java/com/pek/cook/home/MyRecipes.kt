package com.pek.cook.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pek.cook.R
import com.pek.cook.Recipe
import com.pek.cook.model.getFavoriteRecipesFromFirestore
import com.pek.cook.nav.NavRoutes
import com.pek.cook.ui.theme.neu3
import com.pek.cook.ui.theme.neu4


@Composable
fun MyRecipe(navController: NavController) {

    val currentUser = Firebase.auth.currentUser
    val userId = currentUser?.uid ?: ""
    val recipeListState = remember { mutableStateOf<List<Recipe>>(emptyList()) }


    Surface(
        modifier = Modifier
            .background(neu3)
            .fillMaxSize(),
        color = neu3
    ) {

        // Retrieve favorite recipe list from Firestore and update state
        getFavoriteRecipesFromFirestore(userId) { recipeList ->
            recipeListState.value = recipeList
        }

        if (recipeListState.value.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.cookmate),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Favorite list is empty!",
                    color = neu4,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(recipeListState.value) { recipe ->
                    RecipeListItem(recipe = recipe, onClick = { id ->
                        val route = NavRoutes.RecipeDetails.createRoute(id)
                        navController.navigate(route)
                    })
                }
            }
        }
    }
}


