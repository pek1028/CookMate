package com.pek.cook.model

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pek.cook.MainActivity.Companion.TAG
import com.pek.cook.Recipe


// Add the recipe document to the user's recipe collection in Firestore
fun addRecipeToFirestore(userId: String, recipe: Recipe) {
    val db = Firebase.firestore
    val recipeData = hashMapOf(
        "id" to recipe.id,
        "name" to recipe.name,
        "description" to recipe.description,
        "ingredients" to recipe.ingredients,
        "step" to recipe.step,
        "image" to recipe.image,
        "course" to recipe.course,
        "time" to recipe.time,
        "isFavorite" to recipe.isFavorite,
        "isIngredient" to recipe.isIngredient
    )
    db.collection("users")
        .document(userId)
        .collection("recipes")
        .document(recipe.id.toString())
        .set(recipeData)
        .addOnSuccessListener {
            Log.d(TAG, "Recipe document added to Firestore")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding recipe document", e)
        }
}


fun addToFavorites(recipe: Recipe, userId: String) {
    // Update the favorite status of the recipe
    recipe.isFavorite = true

    // Add the recipe to the user's favorites in Firestore
    val db = Firebase.firestore
    val recipeData = hashMapOf(
        "id" to recipe.id,
        "name" to recipe.name,
        "description" to recipe.description,
        "ingredients" to recipe.ingredients,
        "step" to recipe.step,
        "image" to recipe.image,
        "course" to recipe.course,
        "time" to recipe.time,
        "isFavorite" to recipe.isFavorite,
        "isIngredient" to recipe.isIngredient
    )

    db.collection("users")
        .document(userId)
        .collection("recipes")
        .document(recipe.id.toString())
        .set(recipeData)
        .addOnSuccessListener {
            Log.d(TAG, "Recipe document added to Firestore")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding recipe document", e)
        }

    // Update the user's list of favorite recipes
    db.collection("users")
        .document(userId)
        .update("favoriteRecipes", FieldValue.arrayUnion(recipe.id))
        .addOnSuccessListener {
            Log.d(TAG, "Recipe added to favorites for user $userId")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding recipe to favorites for user $userId", e)
        }
}

fun removeRecipeFromFavorites(recipe: Recipe, userId: String) {
    // Remove the recipe document from the user's recipe collection in list
    recipe.isFavorite = false
    val db = Firebase.firestore
    db.collection("users")
        .document(userId)
        .collection("recipes")
        .document(recipe.id.toString())
        .delete()
        .addOnSuccessListener {
            Log.d(TAG, "Recipe document deleted for user $userId with recipe ID ${recipe.id}")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error deleting recipe",e)
        }
}

fun getFavoriteRecipesFromFirestore(userId: String, onRecipeListRetrieved: (List<Recipe>) -> Unit) {
    val db = Firebase.firestore
    db.collection("users")
        .document(userId)
        .collection("recipes")
        .whereEqualTo("isFavorite", true)
        .get()
        .addOnSuccessListener { result ->
            val recipeList = mutableListOf<Recipe>()
            for (document in result) {
                val recipe = document.toObject(Recipe::class.java)
                recipeList.add(recipe)
            }
            onRecipeListRetrieved(recipeList)
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error getting favorite recipe documents", e)
        }
}

fun removeIngredient(recipe: Recipe, userId: String) {
    // Remove the recipe document from the user's recipe collection in list
    recipe.isIngredient = false
    val db = Firebase.firestore
    db.collection("users")
        .document(userId)
        .collection("ingredients")
        .document(recipe.id.toString())
        .delete()
        .addOnSuccessListener {
            Log.d(TAG, "Recipe document deleted for user $userId with recipe ID ${recipe.id}")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error deleting recipe",e)
        }
}

fun getIngredientsFromFirestore(userId: String, onRecipeListRetrieved: (List<Recipe>) -> Unit) {
    val db = Firebase.firestore
    db.collection("users")
        .document(userId)
        .collection("ingredients")
        .whereEqualTo("isIngredient", true)
        .get()
        .addOnSuccessListener { result ->
            val recipeList = mutableListOf<Recipe>()
            for (document in result) {
                val recipe = document.toObject(Recipe::class.java)
                recipeList.add(recipe)
            }
            onRecipeListRetrieved(recipeList)
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error getting ingredients documents", e)
        }
}

// Add the recipe document to the user's recipe collection in Firestore
fun addListToFirestore(userId: String, recipe: Recipe) {
    val db = Firebase.firestore
    val recipeData = hashMapOf(
        "id" to recipe.id,
        "name" to recipe.name,
        "description" to recipe.description,
        "ingredients" to recipe.ingredients,
        "step" to recipe.step,
        "image" to recipe.image,
        "course" to recipe.course,
        "time" to recipe.time,
        "isFavorite" to recipe.isFavorite,
        "isIngredient" to recipe.isIngredient
    )
    db.collection("users")
        .document(userId)
        .collection("ingredients")
        .document(recipe.id.toString())
        .set(recipeData)
        .addOnSuccessListener {
            Log.d(TAG, "Ingredients added to Firestore")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding ingredients", e)
        }
}


fun addToShopping(recipe: Recipe, userId: String) {
    // Update the favorite status of the recipe
    recipe.isIngredient = true

    // Add the recipe to the user's favorites in Firestore
    val db = Firebase.firestore
    val recipeData = hashMapOf(
        "id" to recipe.id,
        "name" to recipe.name,
        "description" to recipe.description,
        "ingredients" to recipe.ingredients,
        "step" to recipe.step,
        "image" to recipe.image,
        "course" to recipe.course,
        "time" to recipe.time,
        "isFavorite" to recipe.isFavorite,
        "isIngredient" to recipe.isIngredient
    )

    db.collection("users")
        .document(userId)
        .collection("ingredients")
        .document(recipe.id.toString())
        .set(recipeData)
        .addOnSuccessListener {
            Log.d(TAG, "Ingredients added to Firestore")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding recipe document", e)
        }

    // Update the user's list of favorite recipes
    db.collection("users")
        .document(userId)
        .update("IngredientList", FieldValue.arrayUnion(recipe.id))
        .addOnSuccessListener {
            Log.d(TAG, "Recipe added to favorites for user $userId")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding recipe to favorites for user $userId", e)
        }
}