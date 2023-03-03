package com.pek.cook.home

import android.annotation.SuppressLint
import android.util.Log
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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pek.cook.MainActivity.Companion.TAG
import com.pek.cook.Recipe
import com.pek.cook.RecipeDatabase
import com.pek.cook.nav.RecipeCard
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
            Button(
                onClick = {
                    if(!recipe.isIngredient){
                        recipe.isIngredient = true
                        Toast.makeText(context, "Ingredient added to List", Toast.LENGTH_SHORT).show()
                    }else{
                        recipe.isIngredient = false
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
                if(!recipe.isIngredient){
                    Text(
                        "Add to Shopping List",
                        color = neu4)
                }else{
                    Text(
                        "Remove from Shopping List",
                        color = neu4)
                }
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
            Button(
                onClick = {
                    if(!recipe.isFavorite){
                        recipe.isFavorite = true
                        addToFavorites(recipe)
                        Toast.makeText(context, "Added to Favourite", Toast.LENGTH_SHORT).show()
                    }else{
                        recipe.isFavorite = false
                        removeFromFavorites(recipe)
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
                if(!recipe.isFavorite){
                    Text(
                        "Add to Favourite",
                        color = neu4)
                }else{
                    Text(
                        "Remove from favourite",
                        color = neu4)
                }
            }
            Spacer(modifier = Modifier.height(70.dp))
        }

    }

}

@SuppressLint("StaticFieldLeak")
val db = Firebase.firestore
val favoritesRef = db.collection("favorites")
fun addToFavorites(recipe: Recipe) {
    recipe.isFavorite = true // Update the favorite status
    val document = favoritesRef.document(recipe.id.toString())
    document.set(recipe)
        .addOnSuccessListener {
            Log.d(TAG, "Recipe added to favorites: ${recipe.id}")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding recipe to favorites", e)
        }
}

fun removeFromFavorites(recipe: Recipe) {
    val document = favoritesRef.document(recipe.id.toString())
    document.delete()
        .addOnSuccessListener {
            Log.d(TAG, "Recipe removed from favorites: ${recipe.id}")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error removing recipe from favorites", e)
        }
}


@Preview(showBackground = true)
@Composable
fun RDPreview(){
    RecipeDetail(rememberNavController(), id = 3)
}