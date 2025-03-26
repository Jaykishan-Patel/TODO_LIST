package com.example.todolist

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolist.Data.Wish

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    viewModel: WishViewModel,
    navController: NavController,
    modifier: Modifier
){
    val context= LocalContext.current
    Scaffold (
        modifier=modifier,
        topBar = {
           AppBar(title = "WishList") {
               Toast.makeText(context, "click is done on floating button", Toast.LENGTH_LONG).show()
           }
        },
        floatingActionButton = {

                FloatingActionButton(modifier = Modifier.padding(20.dp),
                    onClick = {
                        navController.navigate(Screen.AddScreen.route+"/0L")
                              Toast.makeText(context,"click is done on floating button",Toast.LENGTH_LONG).show()
                    },
                    contentColor = Color.White,
                    backgroundColor = Color.Blue
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Floating add button")
                }

        }
    ){
            val wishList=viewModel.getAllWishes.collectAsState(initial =listOf())

              LazyColumn(modifier= Modifier
                  .fillMaxSize()
                  .padding(it)){
                  items(wishList.value, key = {wish-> wish.id}){ wish->

                      val dismissState= rememberDismissState(
                          confirmStateChange = {
                              if(it==DismissValue.DismissedToStart || it==DismissValue.DismissedToEnd){
                                  viewModel.deleteWish(wish)
                              }
                              true
                          }
                      )
                      SwipeToDismiss(
                          state = dismissState,
                          background ={
                                      val color by animateColorAsState(
                                          if (dismissState.dismissDirection==DismissDirection.EndToStart) Color.Red else Color.Transparent,
                                          label = ""
                                      )
                              Box (modifier= Modifier
                                  .fillMaxSize()
                                  .background(color)
                                  .padding(horizontal = 20.dp),
                                  contentAlignment =Alignment.CenterEnd){
                                  Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                              }
                          } ,
                          directions = setOf(DismissDirection.EndToStart),
                          dismissThresholds = {FractionalThreshold(1f)}
                          ,dismissContent ={
                              WishItem(wish = wish) {
                                  val id=wish.id
                                  navController.navigate(Screen.AddScreen.route+"/$id")
                              }
                          } )

                  }
              }

    }
}

@Composable
fun WishItem(wish: Wish,onclick:()->Unit){
    Card (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { onclick() },
        elevation = 10.dp

    ){
        Column (modifier = Modifier.padding(16.dp)){
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.description)
        }

    }
}