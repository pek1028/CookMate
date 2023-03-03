package com.pek.cook.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pek.cook.Recipe

// Define the data structure for a user's favorites in Firestore
data class UserFavorites(
    val userId: String,
    val favoriteRecipeIds: List<String>
)

// Update the favorite status of a recipe and save to Firestore
fun updateFavoriteStatus(recipe: Recipe, isFavorite: Boolean, userId: String) {
    // Update the local data source
    recipe.isFavorite = isFavorite

    // Update the Firestore document for the user
    val db = Firebase.firestore
    db.collection("user_favorites")
        .document(userId)
        .get()
        .addOnSuccessListener { document ->
            val favorites = document.toObject(UserFavorites::class.java)
            if (favorites != null) {
                val newFavorites = if (isFavorite) {
                    favorites.favoriteRecipeIds + recipe.id.toString()
                } else {
                    favorites.favoriteRecipeIds - recipe.id.toString()
                }
                db.collection("user_favorites")
                    .document(userId)
                    .set(UserFavorites(userId, newFavorites))
            } else {
                val newFavorites = if (isFavorite) {
                    listOf(recipe.id.toString())
                } else {
                    emptyList()
                }
                db.collection("user_favorites")
                    .document(userId)
                    .set(UserFavorites(userId, newFavorites))
            }
        }
}

// Retrieve a user's list of favorite recipe IDs from Firestore and update the local data source
fun updateFavoriteRecipesForUser(userId: String) {
    val db = Firebase.firestore
    db.collection("user_favorites")
        .document(userId)
        .get()
        .addOnSuccessListener { document ->
            val favorites = document.toObject(UserFavorites::class.java)
            """if (favorites != null) {
                // Update the "isFavorite" property of each recipe in the local data source
                recipes.forEach { recipe ->
                    recipe.isFavorite = favorites.favoriteRecipeIds.contains(recipe.id.toString())
                }
            }"""
        }
}

// Update a user's list of favorite recipe IDs in Firestore
fun updateUserFavorites(userId: String, favoriteRecipeIds: List<String>) {
    val db = Firebase.firestore
    db.collection("user_favorites")
        .document(userId)
        .set(UserFavorites(userId, favoriteRecipeIds))
}
