package com.example.littlelemonandroidcapstone

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController


@Composable
fun Onboarding(
    navController: NavHostController
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(USER_PROFILE, Context.MODE_PRIVATE)

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
                text = "Let's get to know you",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFFF4CE14)
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier
                    .size(width = 350.dp, height = 70.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier
                    .size(width = 350.dp, height = 70.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .size(width = 350.dp, height = 70.dp)
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
                    if (
                        firstName.isBlank() ||
                        lastName.isBlank() ||
                        email.isBlank()
                    ) {
                        Toast.makeText(
                            context,
                            "Registration unsuccessful. Please enter all data.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        sharedPreferences.edit(commit = true) { putString(FIRST_NAME, firstName) }
                        sharedPreferences.edit(commit = true) { putString(LAST_NAME, lastName) }
                        sharedPreferences.edit(commit = true) { putString(EMAIL, email) }
                        Toast.makeText(
                            context, "Registration successful!",
                            Toast.LENGTH_SHORT).show()
                        navController.navigate("Home")
                    }
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
                    "Register",
                    color = Color(
                        0xFFF4CE14
                    )
                )
            }
            }
        }
    }

//@Preview
//@Composable
//fun OnboardingPreview(navController: NavHostController) {
//    Onboarding(navController)
//}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(3f)
                .padding(20.dp)
        )
    }
}

//@Composable
//fun FirstNameTextField() {
//    var firstName by remember { mutableStateOf("") }
//
//    TextField(
//        value = firstName,
//        onValueChange = { firstName = it },
//        label = { Text("First Name") },
//        modifier = Modifier
//            .size(width = 350.dp, height = 70.dp)
//    )
//}
//
//@Composable
//fun LastNameTextField() {
//    var lastName by remember { mutableStateOf("") }
//
//    TextField(
//        value = lastName,
//        onValueChange = { lastName = it },
//        label = { Text("Last Name") },
//        modifier = Modifier
//            .size(width = 350.dp, height = 70.dp)
//    )
//}
//
//@Composable
//fun EmailTextField() {
//    var email by remember { mutableStateOf("") }
//
//    TextField(
//        value = email,
//        onValueChange = { email = it },
//        label = { Text("Email") },
//        modifier = Modifier
//            .size(width = 350.dp, height = 70.dp)
//    )
//}

