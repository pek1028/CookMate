package com.pek.cook.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pek.cook.RecipeDatabase
import com.pek.cook.nav.NavRoutes
import com.pek.cook.ui.theme.neu3


@Composable
fun MyRecipe(navController: NavController) {
    Surface(
        modifier = Modifier.background(neu3),
        color = neu3
    ) {
        LazyColumn(
        ){
            items(RecipeDatabase.recipeList.filter { true }) {
                RecipeListItem(recipe = it, onClick = { id ->
                    Log.d("RecipeListItem", "Clicked recipe with id $id")
                    navController.navigate(NavRoutes.RecipeDetails.createRoute(it.id))
                })
            }
        }
    }
}

@Preview
@Composable
fun MRPreview(){
    MyRecipe(rememberNavController())
}