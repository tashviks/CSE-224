package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyHomeScreen()
        }
    }

    @Composable
    fun MyAnimation() {

        var visible by remember {
            mutableStateOf(true)
        }

        Column {
            AnimatedVisibility(visible = visible) {
                Text(text = "Hello, world!")
            }

            Button(onClick = { visible = !visible }) {
                Text(text = "Button", style = MaterialTheme.typography.button)
            }
        }
    }

    @Composable
    fun MyHomeScreen() {
        LittleLemonTheme {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Home.route) {
                composable(Home.route) {
                    HomeScreen(navController)
                }
                composable(
                    DishDetails.route + "/{${DishDetails.argDishId}}",
                    arguments = listOf(navArgument(DishDetails.argDishId) {
                        type = NavType.IntType
                    })
                ) {
                    val id =
                        requireNotNull(it.arguments?.getInt(DishDetails.argDishId)) { "Dish id is null" }
                    DishDetails(id)
                }
            }
        }
    }
}
