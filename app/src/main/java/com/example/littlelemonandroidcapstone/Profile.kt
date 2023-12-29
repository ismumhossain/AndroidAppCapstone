package com.example.littlelemonandroidcapstone

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Profile( navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(USER_PROFILE, Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString(FIRST_NAME, "N/A")
    val lastName = sharedPreferences.getString(LAST_NAME, "N/A")
    val email = sharedPreferences.getString(EMAIL, "N/A")

    Column {
        Header()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFF495E57)),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = "Personal Information",
                style = typography.headlineLarge,
                color = Color(0xFFF4CE14)
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = "First Name: $firstName",
                style = typography.bodyLarge,
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = "Last Name: $lastName",
                style = typography.bodyLarge,
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = "Email: $email",
                style = typography.bodyLarge,
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    sharedPreferences.edit().clear().apply()

                    navController.navigate(Onboarding.route) {
                        popUpTo(Onboarding.route) { inclusive = true } }
                    },
                    colors = ButtonDefaults
                        .buttonColors(
                            Color(
                                0xFF495E57
                            )
                        ),
                    modifier = Modifier
                        .height(40.dp)
            ) {
                Text(
                        "Log out",
                        color = Color(
                            0xFFF4CE14
                        )
                )
            }
        }
    }
}

//@Preview()
//@Composable
//fun ProfilePreview(navController: NavHostController) {
//    Profile(navController)
//}
