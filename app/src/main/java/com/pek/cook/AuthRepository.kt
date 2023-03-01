package com.pek.cook

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ktx.auth;
import com.google.firebase.ktx.Firebase;

class AuthRepository {
    val currentUser:FirebaseUser? = Firebase.auth.currentUser
}

private var isLoggedIn = false

fun setIsLoggedIn(value: Boolean) {
    isLoggedIn = value
}

private val auth = FirebaseAuth.getInstance()

fun isLoggedIn(): Boolean {
    return auth.currentUser != null
}
