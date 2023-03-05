package com.pek.cook.login

import com.google.firebase.auth.FirebaseAuth

private val auth = FirebaseAuth.getInstance()

fun isLoggedIn(): Boolean {
    return auth.currentUser != null
}
