package com.example.littlelemonandroidcapstone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController: NavController, database: AppDatabase) {
    val databaseMenuItems: List<MenuItemRoom> by database
        .menuItemDao()
        .getAll()
        .observeAsState(emptyList())

    Column {
        TopAppBar(navController)
        Hero(menuItemsData = databaseMenuItems)
    }
}

@Composable
fun Hero(menuItemsData: List<MenuItemRoom>) {
    var menuItems = menuItemsData
    var selectedCategory by remember { mutableStateOf("") }

    Column {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0xFF495E57)),
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = "Little Lemon",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF4CE14),
                    modifier = Modifier
                        .padding(horizontal = 25.dp, vertical = 10.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0xFF495E57)),
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = "Chicago",
                    fontSize = 22.sp,
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier
                        .padding(horizontal = 25.dp, vertical = 5.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding()
                    .background(Color(0xFF495E57))
            ) {
                Text(
                    text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 10.dp)
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth(0.6f),
                    textAlign = TextAlign.Left,
                    fontSize = 16.sp,
                    color = Color(0xFFFFFFFF)
                )
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Hero Image",
                    modifier = Modifier
                        .fillMaxWidth(1F)
                        .clip(RoundedCornerShape(10.dp))
                        .padding(horizontal = 10.dp)
                )
            }
        }
        Column {
            var searchPhrase by remember { mutableStateOf("") }

            OutlinedTextField(
                label = { Text(text = "Enter search phrase") },
                value = searchPhrase,
                onValueChange = { searchPhrase = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp),
                leadingIcon = {
                    Icon(
                        Icons.Default.Search, contentDescription = "Search"
                    )
                },
            )
            if (searchPhrase.isNotEmpty()) {
                menuItems =
                    menuItems.filter { it.title.contains(searchPhrase, ignoreCase = true) }
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            val scrollState = rememberScrollState()

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
                    .horizontalScroll(scrollState)
            ) {
                Button(
                    onClick = {
                        selectedCategory = "starters"
                    }, modifier = Modifier
                        .padding(5.dp)
                        .height(40.dp),
                    colors = ButtonDefaults
                        .buttonColors(
                            Color(0xFFF4CE14)
                        )
                ) {
                    Text(text = "Starters",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF495E57),
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        selectedCategory = "mains"
                    }, modifier = Modifier
                        .padding(5.dp)
                        .height(40.dp),
                    colors = ButtonDefaults
                        .buttonColors(
                            Color(0xFFF4CE14)
                        )
                ) {
                    Text(text = "Mains",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF495E57),
                        fontWeight = FontWeight.Bold
                        )
                }

                Button(
                    onClick = {
                        selectedCategory = "desserts"
                    }, modifier = Modifier
                        .padding(
                            5.dp
                        )
                        .height(40.dp),
                    colors = ButtonDefaults
                        .buttonColors(
                            Color(0xFFF4CE14)
                        )
                ) {
                    Text(text = "Desserts",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF495E57),
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        selectedCategory = "drinks"
                    }, modifier = Modifier
                        .padding(5.dp)
                        .height(40.dp),
                    colors = ButtonDefaults
                        .buttonColors(
                            Color(0xFFF4CE14)
                        )
                ) {
                    Text(text = "Drinks",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF495E57),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            if (selectedCategory.isNotEmpty()) {
                menuItems = menuItems.filter { it.image.contains(selectedCategory) }
            }
            MenuItems(menuItems)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(items: List<MenuItemRoom>) {
    LazyColumn() {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(Color(0xFF495E57))
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(0.6f)
                    ) {
                        Text(text = item.title,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color(0xFFF4CE14)
                        )
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color(0xFFFFFFFF)
                        )
                        Text(
                            text = "Price: ${item.price}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color(0xFFFFFFFF)
                        )
                    }
                    GlideImage(
                        model = item.category,
                        contentDescription = "Menu Item Image",
                        modifier = Modifier
                            .scale(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxWidth(0.9f)
                            .align(Alignment.CenterVertically),
                        alignment = Alignment.Center
                    )
                }
            }
        }
    }
}