package com.pek.cook.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pek.cook.Recipe
import com.pek.cook.RecipeDatabase
import com.pek.cook.nav.NavRoutes
import com.pek.cook.ui.theme.neu2
import com.pek.cook.ui.theme.neu3

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyList(
    navController: NavController,
) {
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


@Composable
fun RecipeListItem(recipe: Recipe, onClick: (Int) -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        elevation = 2.dp,
        backgroundColor = neu2,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier.clickable{onClick(recipe.id)}
        ){
            RecipeImage(recipe = recipe)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = recipe.name, style = typography.h6)
                Text(text = "View Detail", style = typography.caption)
            }
        }
    }
}

@Composable
private fun RecipeImage(recipe: Recipe){
    Image(
        painter = painterResource(id = recipe.image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(90.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}

@Preview(showBackground = true)
@Composable
fun ListPreview(){
    //MyList(rememberNavController(), recipeViewModel)
}