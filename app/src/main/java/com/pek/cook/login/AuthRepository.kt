package com.pek.cook

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ktx.auth;
import com.google.firebase.ktx.Firebase;

private val auth = FirebaseAuth.getInstance()

fun isLoggedIn(): Boolean {
    return auth.currentUser != null
}
