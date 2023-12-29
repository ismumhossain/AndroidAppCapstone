package com.example.littlelemonandroidcapstone

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComposable(navController: NavHostController, database: AppDatabase) {
    NavHost(
        navController = navController,
        startDestination = if (hasData()) Home.route else Onboarding.route
    ) {
        composable(Home.route) { Home(navController, database) }
        composable(Onboarding.route) { Onboarding(navController) }
        composable(Profile.route) { Profile(navController) }
    }
}


@Composable
private fun hasData() : Boolean {
    val context = LocalContext.current
    val sharedPreferences = context
        .getSharedPreferences(
            USER_PROFILE,
            Context.MODE_PRIVATE
        )
    val firstName = sharedPreferences.getString(FIRST_NAME, "") ?: ""
    val lastName = sharedPreferences.getString(LAST_NAME, "") ?: ""
    val email = sharedPreferences.getString(EMAIL, "") ?: ""
    return firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()
}
