package com.example.food_order

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginpage(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val cardBackgroundColor = Color(255, 255, 255, 150)
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
                    .padding(16.dp),

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 45.sp,
                    style = MaterialTheme.typography.headlineLarge.copy(),
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = username,
                    onValueChange = { it -> username = it },
                    label = { Text(text = "User Name", color = Color.Black) },
                    modifier = Modifier
                        .padding(10.dp)
                        .width(280.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(

                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password", color = Color.Black) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                        }

                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .padding(10.dp)
                        .width(280.dp)
                )
                Spacer(modifier = Modifier.height(50.dp))
                Button(

                    modifier = Modifier
                        .width(200.dp)
                        .height(56.dp),

                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    onClick = { navController.navigate("restaurant_list") },
                ) {
                    Text(
                        "Login",
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Login account"
                    )
                }
            }
        }
    }
}
