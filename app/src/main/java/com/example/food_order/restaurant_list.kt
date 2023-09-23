package com.example.food_order

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Restaurant(
    val name: String, val address: String,
    val imageResourceId: Int
)

@Composable
fun restaurant_list(
    navController: NavController,
    onRestaurantClick: (Restaurant) -> Unit
) {
    val restaurantsList = listOf(
        Restaurant("Domino's", "Indiranagar, Bangalore", R.drawable.dominos),
        Restaurant("KFC", "HSR Layout, Bangalore", R.drawable.kfc),
        Restaurant("Bhartiya Jalpan", "HAL Bangalore", R.drawable.sweet),
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.back3),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Restaurants",
                fontSize = 45.sp,
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(restaurantsList) { restaurant ->
                    RestaurantItem(restaurant = restaurant,
                        onClick = { onRestaurantClick(restaurant) }
                    )
                }
            }
        }
    }
}

@Composable
fun RestaurantItem(
    restaurant: Restaurant,
    onClick: (Restaurant) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(restaurant) }
            .padding(8.dp),


        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = restaurant.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Text(
                text = restaurant.name,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontStyle = FontStyle.Italic
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = restaurant.address,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Gray
                )
            )
        }
    }

}

