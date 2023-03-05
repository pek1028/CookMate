package com.pek.cook.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pek.cook.datas.RecipeDatabase
import com.pek.cook.model.*
import com.pek.cook.datas.RecipeCard
import com.pek.cook.ui.theme.d_gray
import com.pek.cook.ui.theme.neu1
import com.pek.cook.ui.theme.neu3
import com.pek.cook.ui.theme.neu4

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeDetail(navController: NavController, id:Int){
    Scaffold(
        content = {
            DetailsView(id)
        }
    )

}

@Composable
fun DetailsView(id: Int) {

    val currentUser = Firebase.auth.currentUser
    val userId = currentUser?.uid ?: ""
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = neu1)
    ) {

        val recipe = RecipeDatabase.recipeList[id]

        // Basic details
        item {
            recipe.apply {

                val recipeImage: Painter = painterResource(id = recipe.image)
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp),
                    painter = recipeImage,
                    alignment = Alignment.CenterStart,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                RecipeCard(name, description, course)
            }
        }

        item {
            recipe.apply {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Ingredients:",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    color = neu4,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(10.dp))
                for (step in recipe.ingredients) {
                    Text(
                        text = step,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 0.dp, 16.dp, 0.dp),
                        color = neu4,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(36.dp))
            val buttonText = remember(recipe.isIngredient) {
                if (!recipe.isIngredient) {
                    "Add to Shopping List"
                } else {
                    "Remove from Shopping List"
                }
            }
            Button(
                onClick = {
                    if(!recipe.isIngredient){
                        recipe.isIngredient = true
                        addListToFirestore(userId, recipe)
                        addToShopping(recipe, userId)
                        Toast.makeText(context, "Ingredient added to List", Toast.LENGTH_SHORT).show()
                    }else{
                        recipe.isIngredient = false
                        removeIngredient(recipe, userId)
                        Toast.makeText(context, "Removed from List", Toast.LENGTH_SHORT).show()

                    }

                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = neu3,
                    contentColor = d_gray
                )
            ) {
                Text(buttonText)
            }
        }

        item {
            recipe.apply {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Steps:",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    color = neu4,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(10.dp))
                for (step in recipe.step) {
                    Text(
                        text = step,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 0.dp, 16.dp, 0.dp),
                        color = neu4,
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }
        }

        // Quick info
        item {
            recipe.apply {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoCard(title = "ID", value = recipe.id.toString())
                    InfoCard(title = "Time", value = recipe.time.toString() + " min")
                    InfoCard(title = "Course", value = recipe.course)
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(36.dp))
            val buttonText = remember(recipe.isFavorite) {
                if (!recipe.isFavorite) {
                    "Add to Favourite"
                } else {
                    "Remove from Favourite"
                }
            }
            Button(
                onClick = {
                    if(!recipe.isFavorite){
                        recipe.isFavorite = true
                        addRecipeToFirestore(userId, recipe)
                        addToFavorites(recipe, userId)
                        Toast.makeText(context, "Added to Favourite", Toast.LENGTH_SHORT).show()
                    }else{
                        recipe.isFavorite = false
                        removeRecipeFromFavorites(recipe, userId)
                        Toast.makeText(context, "Removed from Favourite", Toast.LENGTH_SHORT).show()

                    }
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = neu3,
                    contentColor = d_gray
                )
            ) {
                Text(buttonText)
            }
            Spacer(modifier = Modifier.height(70.dp))
        }

    }

}


@Preview(showBackground = true)
@Composable
fun RDPreview(){
    RecipeDetail(rememberNavController(), id = 3)
}