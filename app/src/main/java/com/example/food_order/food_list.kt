package com.example.food_order

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class FoodItem(
    val name: String, val description: String, val price: Float, val imageResourceId: Int
)

@Composable
fun food_list(
    navController: NavController,
    restaurant: Restaurant,
    cart: Cart,
    onFoodItemSelected: (FoodItem) -> Unit,
    onCartClick: () -> Unit

) {

    // Sample list of food items for a restaurant
    val foodItems1 = mutableListOf(
        // Add more food items as needed
        FoodItem(
            "Non-Veg Supreme",
            "Freshly baked pizza with toppings of chicken susage",
            499f,
            R.drawable.nonvegsupreme
        ),
        FoodItem(
            "Chicken Golden Delight",
            "Freshly baked pizza with toppings of chicken",
            499f,
            R.drawable.chickendelight
        ),
        FoodItem(
            "Indi Chicken Tikka",
            "Freshly baked pizza with toppings of chicken tikka with butter",
            599f,
            R.drawable.chickentikka
        ),
        // Add more food items as needed
        FoodItem("Choco Lava Cake", "Cake with chocolate filling", 99f, R.drawable.lava),
        FoodItem(
            "Garlic Bread Sicks",
            "Freshly baked bread sticks with garlic and cheese",
            199f,
            R.drawable.garlicbread
        ),
        FoodItem(
            "Veg Pizza", "Freshly baked pizza with toppings of vegetables", 299f, R.drawable.pizza
        ),
        FoodItem(
            "Peppy Paneer",
            "Freshly baked pizza with toppings of paneer",
            399f,
            R.drawable.peppypanner
        ),
        FoodItem(
            "Veg Paradise",
            "Freshly baked pizza with toppings of paneer, mushroom and cron",
            399f,
            R.drawable.paradise
        )
        // Add more food items as needed
    )

    val foodItems2 = mutableListOf(
        FoodItem(
            "Veg Burger", "Delicious burger with cheese and veggies", 250f, R.drawable.vegburger
        ),
        FoodItem(
            "Chicken Burger",
            "Delicious burger with cheese and chicken",
            350f,
            R.drawable.chickenburger
        ),
        FoodItem(
            "Chicken and Krispers Combo",
            "Delicious burger with cheese and chicken wings",
            400f,
            R.drawable.chickencombo
        ),
        // Add more food items as needed
        FoodItem("French Fries", "Delicious potato fries with cheese", 150f, R.drawable.fries),
        FoodItem(
            "Chicken Biryani Bucket",
            "Delicious biryani with chicken leg piece",
            500f,
            R.drawable.chickenbiryani
        ),
        FoodItem("Chicken Wings", "Delicious chicken wings", 450f, R.drawable.chickenwings),
    )

    val foodItems3 = mutableListOf(
        FoodItem("Kaju Katli", "Delicious sweet made of kaju", 599f, R.drawable.kaju),
        FoodItem(
            "Jalebi", "Delicious sweet made of sugar syrup and fried dough", 399f, R.drawable.jalebi
        ),
        FoodItem("Laddu", "Delicious sweet made of soft dough and sugar", 499f, R.drawable.laddu),
        // Add more food items as needed
        FoodItem(
            "Rasgulla", "Delicious sweet made of paneer and sugar syrup", 599f, R.drawable.rasgulla
        ),

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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("restaurant_list")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
                Text(
                    text = restaurant.name,
                    fontSize = 45.sp,
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f), userScrollEnabled = true
            ) {
                if (restaurant.name.equals("Domino's")) {
                    items(foodItems1) { foodItem ->
                        FoodItemCard(
                            foodItem = foodItem,
                            onFoodItemSelected = onFoodItemSelected,
                            onAddToCartClick = { cart.addItem(foodItem, 1) },
                            onRemoveFromCartClick = { cart.removeItem(foodItem) },
                            quantity = cart.getQuantity(foodItem = foodItem)
                        )
                    }
                } else if (restaurant.name.equals("KFC")) {
                    items(foodItems2) { foodItem ->
                        FoodItemCard(
                            foodItem = foodItem,
                            onFoodItemSelected = onFoodItemSelected,
                            onAddToCartClick = { cart.addItem(foodItem, 1) },
                            onRemoveFromCartClick = { cart.removeItem(foodItem) },
                            quantity = cart.getQuantity(foodItem = foodItem)
                        )
                    }
                } else if (restaurant.name.equals("Bhartiya Jalpan")) {
                    items(foodItems3) { foodItem ->
                        FoodItemCard(
                            foodItem = foodItem,
                            onFoodItemSelected = onFoodItemSelected,
                            onAddToCartClick = { cart.addItem(foodItem, 1) },
                            onRemoveFromCartClick = { cart.removeItem(foodItem) },
                            quantity = cart.getQuantity(foodItem = foodItem)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(200.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                onClick = {
                    onCartClick()
                    navController.navigate("cart")
                },
            ) {
                Text(
                    "Go to Cart", fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart"
                )
            }
        }
    }
}


@Composable
fun FoodItemCard(
    foodItem: FoodItem,
    onFoodItemSelected: (FoodItem) -> Unit,
    onAddToCartClick: () -> Unit,
    onRemoveFromCartClick: () -> Unit,
    quantity: Int,
) {
    val cardBackgroundColor = Color(255, 255, 255, 200)
    var quantity by remember { mutableStateOf(quantity) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onFoodItemSelected(foodItem) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = foodItem.imageResourceId),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    text = foodItem.name,
                    modifier = Modifier.padding(4.dp),
                    fontSize = 36.sp,
                    style = MaterialTheme.typography.headlineLarge.copy(),
                    fontFamily = FontFamily.Cursive,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

            }
            Text(
                text = foodItem.description,
                modifier = Modifier.padding(4.dp),
                fontSize = 15.sp,
                style = MaterialTheme.typography.headlineLarge.copy(),
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Left
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Rs. ${foodItem.price}",
                modifier = Modifier.padding(4.dp),
                fontSize = 30.sp,
                style = MaterialTheme.typography.headlineLarge.copy(),
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.width(60.dp))
            IconButton(
                onClick = {
                    quantity++
                    onAddToCartClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Increase Quantity"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$quantity",
                modifier = Modifier.padding(4.dp),
                fontSize = 20.sp,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    if (quantity > 0) {
                        quantity--
                        onRemoveFromCartClick()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Decrease Quantity"
                )
            }
        }
    }
}
