package com.pek.cook.home

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pek.cook.R
import com.pek.cook.datas.Recipe
import com.pek.cook.datas.getIngredientsFromFirestore
import com.pek.cook.nav.NavRoutes
import com.pek.cook.ui.theme.*

@Composable
fun MyList(
    navController: NavController,
) {

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
        getIngredientsFromFirestore(userId) { recipeList ->
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
                    text = "Shopping list is empty!",
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
                    Ingredients(recipe = recipe, onClick = { id ->
                        val route = NavRoutes.RecipeDetails.createRoute(id)
                        navController.navigate(route)
                    })
                }
            }
        }
    }
}


@Composable
fun Ingredients(recipe: Recipe, onClick: (Int) -> Unit){
    Card(
        modifier = Modifier
            .width(360.dp)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        elevation = 2.dp,
        backgroundColor = neu1,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier.clickable{onClick(recipe.id)}
        ){
            Column {
                Text(text = recipe.name,
                    style = typography.h5,
                    color = neu4,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp, 2.dp)
                )
                Text(text = "Ingredients:",
                    style = typography.h6,
                    modifier = Modifier
                        .padding(8.dp, 2.dp),
                    color = neu4,
                    fontWeight = FontWeight.Normal
                )
                for (step in recipe.ingredients) {
                    Text(
                        text = step,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, 8.dp),
                        color = neu4,
                        style = typography.h6,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal
                    )
                }
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
    MyList(rememberNavController())
}