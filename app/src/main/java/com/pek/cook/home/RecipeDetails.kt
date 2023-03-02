package com.pek.cook.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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

        // CTA - Adopt me button
        item {
            Spacer(modifier = Modifier.height(36.dp))
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = neu3,
                    contentColor = d_gray
                )
            ) {
                Text(
                    "Add to favourite",
                    color = neu4)
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