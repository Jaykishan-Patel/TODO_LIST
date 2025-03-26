package com.example.todolist


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolist.Data.Wish

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController,
    modifier: Modifier
){
    val snackMessage=remember{ mutableStateOf("") }
    val scope= rememberCoroutineScope()
    val scaffoldState= rememberScaffoldState()

    if(id!=0L){
        val wish=viewModel.getAWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitleState=wish.value.title
        viewModel.wishDescriptionState=wish.value.description

    }
    else{
        viewModel.wishTitleState=""
        viewModel.wishDescriptionState=""
    }

    Scaffold(modifier=modifier
        ,topBar = {AppBar(title =
    if(id != 0L) "Update Wish"
    else "Add Wish"
    ) {navController.navigateUp()}
    },scaffoldState=scaffoldState

        ) {padding->
        Column(modifier = Modifier
            .padding(padding)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Title",
                value = viewModel.wishTitleState,
                onValueChanged = {
                    viewModel.onWishTitleChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChanged = {
                    viewModel.onWishDescriptionChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick={
                if(viewModel.wishTitleState.isNotEmpty() &&
                    viewModel.wishDescriptionState.isNotEmpty()){
                    if (id!=0L){
                        viewModel.updateWish(Wish(
                            id,
                            viewModel.wishTitleState.trim(),
                            viewModel.wishDescriptionState.trim()
                        ))
                        snackMessage.value="Wish has been updated"
                    }
                    else{
                        viewModel.addWish(
                            Wish(
                            id=0L,
                            viewModel.wishTitleState.trim(),
                            viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage.value="Wish has been added"
                    }

                }else{
                    snackMessage.value="Enter in block create a wish"
                }
//                scope.launch {
//                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
//
//                }
                navController.navigateUp()

            }){
                Text(
                    text = if(id != 0L) "Update Wish"
                    else "Add Wish",
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }

}


@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            // using predefined Color
//            textColor = Color.White,
//            // using our own colors in Res.Values.Color
//            focusedBorderColor = colorResource(id = R.color.white),
//            unfocusedBorderColor = colorResource(id = R.color.white),
//            cursorColor = colorResource(id = R.color.white),
//            focusedLabelColor = colorResource(id = R.color.white),
//            unfocusedLabelColor = colorResource(id = R.color.white),
//        )


    )
}