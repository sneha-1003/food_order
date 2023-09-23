package com.example.food_order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

data class CartItem(val foodItem: FoodItem, var quantity: Int)

class Cart {

    val items = mutableListOf<CartItem>()

    fun addItem(foodItem: FoodItem, quantity: Int) {
        val existingItem = items.find { it.foodItem == foodItem }
        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            items.add(CartItem(foodItem, quantity))
        }
    }

    fun removeItem(foodItem: FoodItem) {
        val existingItem = items.find { it.foodItem == foodItem }
        existingItem?.let {
            if (it.quantity > 1) {
                it.quantity -= 1
            } else {
                items.remove(it)
            }
        }
    }

    fun getQuantity(foodItem: FoodItem) : Int{
        val existingItem = items.find { it.foodItem == foodItem }
        if (existingItem != null) {
            return existingItem.quantity
        } else {
            return 0
        }
    }

    fun calculateTotalCost(): Double {
        var totalCost = 0.0
        for (cartItem in items) {
            totalCost += cartItem.foodItem.price * cartItem.quantity
        }
        return totalCost
    }
}

@Composable
fun CartPage(
    cart: Cart,
    onCartItemSelected: (CartItem) -> Unit,
    onBillClick: () -> Unit,
    onBackClick: () -> Unit
) {
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
                text = "Cart",
                fontSize = 45.sp,
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )

            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f), userScrollEnabled = true
            ){
                items(cart.items) { cartItem ->
                    CartItemCard(
                        cartItem, onCartItemSelected,
                        onAddToCartClick = { cart.addItem(cartItem.foodItem, 1) },
                        onRemoveFromCartClick = { cart.removeItem(cartItem.foodItem) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onBillClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            ) {
                Text("Checkout", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onBackClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            ) {
                Text("Back to food list", fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun CartItemCard(
    cartItem: CartItem,
    onCartItemSelected: (CartItem) -> Unit,
    onAddToCartClick: () -> Unit,
    onRemoveFromCartClick: () -> Unit
) {
    var quantity by remember { mutableStateOf(cartItem.quantity) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCartItemSelected(cartItem) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Image(
                    painter = painterResource(id = cartItem.foodItem.imageResourceId),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = cartItem.foodItem.name,
                    modifier = Modifier.padding(4.dp),
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    fontFamily = FontFamily.Cursive,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(110.dp))
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


