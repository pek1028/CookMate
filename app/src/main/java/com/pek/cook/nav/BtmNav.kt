package com.pek.cook.nav

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->

            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(imageVector = navItem.image,
                        contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }
    }
}


sealed class NavRoutes(val route: String) {
    object Frame : NavRoutes("frame")
    object Home : NavRoutes("home")
    object Search : NavRoutes("search")
    object MyRecipe : NavRoutes("my_recipe")
    object MyList : NavRoutes("my_list")
    object Update : NavRoutes("update")
    object Login : NavRoutes("login")
    object SignUp : NavRoutes("signup")
    object RecipeDetails : NavRoutes("details/{recipeId}") {
        fun createRoute(recipeId: Int): String = "details/$recipeId"
        }
    }


data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = "home"
        ),
        BarItem(
            title = "Search",
            image = Icons.Filled.Search,
            route = "search"
        ),
        BarItem(
            title = "Recipes",
            image = Icons.Filled.Favorite,
            route = "my_recipe"
        ),
        BarItem(
            title = "My List",
            image = Icons.Filled.List,
            route = "my_list"
        )
    )
}