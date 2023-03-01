package com.pek.cook.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pek.cook.Recipe
import com.pek.cook.RecipeDatabase
import com.pek.cook.RecipeDatabase.recipeList
import com.pek.cook.nav.CourseTag
import com.pek.cook.nav.NavRoutes
import com.pek.cook.ui.theme.d_gray
import com.pek.cook.ui.theme.neu3
import com.pek.cook.ui.theme.neu4

@Composable
fun Search(navController: NavController, RecipeList: List<Recipe>) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(neu3),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(modifier = Modifier
            .align(Alignment.TopCenter)
        ){
            Column(modifier = Modifier
                .fillMaxHeight()) {

                var search by remember { mutableStateOf("") }

                TextField(
                    value = search,
                    onValueChange = { search = it},
                    label = {
                        Text(
                            text = "Search for recipes", fontSize = 14.sp, color = neu4) },
                    singleLine = true,
                    leadingIcon = { Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = d_gray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(18.dp))
                LazyColumn{
                    items(recipeList.filter { it.name.contains(search) }) {
                        RecipeListItem(recipe = it, onClick = { id ->
                            Log.d("RecipeListItem", "Clicked recipe with id $id")
                            navController.navigate(NavRoutes.RecipeDetails.createRoute(it.id))
                        })
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipePreview(){
    val recipeList = listOf<Recipe>()
    Search(rememberNavController(), RecipeList = recipeList)

}

@Composable
fun InfoCard(title: String, value: String) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(neu3)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentWidth()
        ) {
            Text(
                text = value,
                modifier = Modifier.fillMaxWidth(),
                color = neu4,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

