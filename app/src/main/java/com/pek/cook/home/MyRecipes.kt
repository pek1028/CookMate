package com.pek.cook.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
        modifier = Modifier
            .background(neu3)
            .fillMaxSize(),
        color = neu3
    ) {
        if(RecipeDatabase.recipeList.isEmpty()){
            Text(text = "Favourite List is empty!")
        }else{
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
    }
}

@Preview(showBackground = true)
@Composable
fun MRPreview(){
    MyRecipe(rememberNavController())
}