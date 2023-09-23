package com.example.food_order

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CartCheckoutPage(
    navController: NavController,
    cart: Cart,
    onCheckoutClick: () -> Unit
) {
    val cardBackgroundColor = Color(255, 255, 255, 200)
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.back3),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {},
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Checkout",
                    fontSize = 50.sp,
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Display selected food items and their quantities
                for (cartItem in cart.items) {
                    var price by remember { mutableStateOf(cartItem.quantity * cartItem.foodItem.price) }
                    Text(
                        text = "${cartItem.foodItem.name} x${cartItem.quantity}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = "Rs. $price",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.End,
                        modifier = Modifier.padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Calculate and display the total cost
                val totalCost = cart.calculateTotalCost()
                Text(
                    text = "Total Cost: Rs $totalCost",
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Add a checkout button
                Button(
                    onClick = {
                        onCheckoutClick()
                        val successMessage = "Your order has been placed successfully!"
                        navController.navigate("success/$successMessage")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                ) {
                    Text(
                        text = "Make Payment",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}
