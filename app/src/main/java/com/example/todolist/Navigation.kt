
package com.example.todolist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(modifier: Modifier=Modifier){
    val navController:NavHostController= rememberNavController()
    val viewModel:WishViewModel= viewModel()
    NavHost(navController = navController, startDestination =Screen.HomeScreen.route){
        composable(Screen.HomeScreen.route) {
            HomeView(viewModel,navController,modifier)
        }
        composable(Screen.AddScreen.route+"/{id}",
            arguments = listOf(
                navArgument("id"){
                    type= NavType.LongType
                    defaultValue=0L
                    nullable=false
                }
            )
        ){entity->
            val id=if(entity.arguments!=null) entity.arguments!!.getLong("id") else 0L
            AddEditDetailView(id = id, viewModel = viewModel, navController =navController,modifier )
        }
    }
}