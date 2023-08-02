package com.example.simplerest.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simplerest.ViewModel.UserViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun UserList(
    viewModel: UserViewModel = UserViewModel()
){
    val value by remember{viewModel.repositories}

    LaunchedEffect(key1 = Unit){
        viewModel.getUsers()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            LazyColumn(){
                items(value.users){ user ->
                    Row(){
                        Text(user.user_id, modifier = Modifier.padding(horizontal = 10.dp))
                        Text(user.name, modifier = Modifier.padding(horizontal = 10.dp))
                        Text(user.group, modifier = Modifier.padding(horizontal = 10.dp))
                    }

                }
            }

            Button(
                onClick = {
                    viewModel.addUser(user_id = "M006", name = "test_name", group = "A")
                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White,
                    disabledContainerColor = Color.LightGray
                ),
            ){
                Text("add user")
            }

        }

    }
}
