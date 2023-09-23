package com.example.food_order

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@SuppressLint("SuspiciousIndentation")
@Composable
fun myapp() {
    val cart = Cart()
    val restaurantsList = listOf(
        Restaurant("Domino's", "Address 1", R.drawable.dominos),
        Restaurant("KFC", "Address 2", R.drawable.kfc),
        Restaurant("Bhartiya Jalpan", "Address 3", R.drawable.sweet),
    )

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "welcomepage"
    ) {
        composable("welcomepage") { welcomepage(navController) }
        composable("loginpage") { loginpage(navController) }
        composable("restaurant_list") {
            restaurant_list(navController,
                onRestaurantClick = { selectedRestaurant ->
                    navController.navigate("food_list/${selectedRestaurant.name}")
                }
            )
        }
        composable("food_list/{restaurantName}") { backStackEntry ->
            val restaurantName = backStackEntry.arguments?.getString("restaurantName")
            val selectedRestaurant = restaurantsList.find { it.name == restaurantName }
            if (selectedRestaurant != null) {
                food_list(
                    navController,
                    restaurant = selectedRestaurant,
                    cart = cart,
                    onFoodItemSelected = { selectedFoodItem ->
                        cart.addItem(selectedFoodItem, 1)

                    },
                    onCartClick = { navController.navigate("Cart") }
                )
            }

        }
        composable("Cart") {
            CartPage(
                cart = cart,
                onCartItemSelected = {},
                onBillClick = { navController.navigate("CartCheckoutPage")},
                onBackClick = { navController.navigate("restaurant_list")}
            )
        }
        composable("CartCheckoutPage") {
            CartCheckoutPage(
                navController = navController,
                cart = cart,
                onCheckoutClick = {}
            )
        }
        composable("success/{message}") { backStackEntry ->
            val message = backStackEntry.arguments?.getString("message") ?: ""
            SuccessScreen(navController, message)
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myapp()
        }
    }
}
