package com.pek.cook.home

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pek.cook.MainActivity.Companion.TAG
import com.pek.cook.R
import com.pek.cook.Recipe
import com.pek.cook.RecipeDatabase
import com.pek.cook.nav.NavRoutes
import com.pek.cook.nav.RecipeCard
import com.pek.cook.ui.theme.neu3
import com.pek.cook.ui.theme.neu4


@Composable
fun MyRecipe(navController: NavController) {
    Surface(
        modifier = Modifier
            .background(neu3)
            .fillMaxSize(),
        color = neu3
    ) {
        val favoriteRecipesState = remember { mutableStateOf(emptyList<Recipe>()) }

        getFavoriteRecipes(
            onSuccess = { favoriteRecipes ->
                favoriteRecipesState.value = favoriteRecipes
            },
            onFailure = { e ->
                Log.w(TAG, "Error getting favorite recipes", e)
            }
        )

        if (RecipeDatabase.recipeList.none { it.isFavorite }) {
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
                    text = "Favourite List is empty!",
                    color = neu4,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        } else {
            LazyColumn()
            {
                items(RecipeDatabase.recipeList.filter { it.isFavorite }) { recipe ->
                    RecipeListItem(recipe = recipe, onClick = { id ->
                        val route = NavRoutes.RecipeDetails.createRoute(id)
                        navController.navigate(route)
                    })
                }
            }
        }
        """if (favoriteRecipesState.value.isEmpty()) {
            Text("No favorite recipes")
        } else {
            LazyColumn {
                items(favoriteRecipesState.value) { recipe ->
                    RecipeListItem(recipe = recipe, onClick = { id ->
                        val route = NavRoutes.RecipeDetails.createRoute(id)
                        navController.navigate(route)
                    })
                }
            }
        }"""
    }
}


fun getFavoriteRecipes(onSuccess: (List<Recipe>) -> Unit, onFailure: (Exception) -> Unit) {
    favoritesRef.get()
        .addOnSuccessListener { querySnapshot ->
            val favoriteRecipes = mutableListOf<Recipe>()
            for (document in querySnapshot.documents) {
                val recipe = document.toObject(Recipe::class.java)
                if (recipe != null) {
                    favoriteRecipes.add(recipe)
                }
            }
            onSuccess(favoriteRecipes)
        }
        .addOnFailureListener { e ->
            onFailure(e)
        }
    }
