package com.example.todolist

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import com.example.todolist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title:String,
    onBackNavClick:()->Unit={}
){
    val navigationIcon:(@Composable ()->Unit)? =
        if(!title.contains("WishList")){
            {
                IconButton(onClick = {onBackNavClick()}) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription =null ,
                        tint = Color.White
                    )
                }
        }
}
    else{
        null
        }

    TopAppBar(
        title = { Text(text = title,
            color = colorResource(id = R.color.white),
            modifier= Modifier
                .padding(start = 8.dp)
                .heightIn(max = 24.dp)) },
        elevation=10.dp,
        backgroundColor = Color.Blue,
        navigationIcon =navigationIcon
        )
}