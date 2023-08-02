package com.example.simplerest.ViewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.simplerest.Model.UserRepositories
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.json.JSONObject

class UserViewModel: ViewModel() {

    val _repositories: MutableState<UserRepositories> = mutableStateOf(
        UserRepositories(
            users = listOf()
        )
    )

    val repositories: MutableState<UserRepositories> = _repositories


    fun getUsers(){
        val url = "https://37a4-211-2-3-199.ngrok-free.app/Users"

        val header: HashMap<String, String> = hashMapOf()
        Fuel.get(url).header(header).responseJson{ request, response, result ->
            Log.d("DEBUG", result.get().obj().toString())
            when(result){
                is Result.Failure -> {
                    val ex = result.getException()
                    if(ex.response.statusCode == 404){
                        // do something if got 404
                        var tmp = UserRepositories(users = listOf())
                        _repositories.value = tmp
                    }
                }

                is Result.Success -> {
                    val tmp = Json.decodeFromString<UserRepositories>(result.get().obj().toString())
//                    var tmp = UserRepositories(users = listOf())
                    _repositories.value = tmp
                }

                else -> {
                    var tmp = UserRepositories(users = listOf())
                    _repositories.value = tmp
                }
            }

        }
    }

    fun addUser(user_id: String, name: String, group: String){
        val url = "https://37a4-211-2-3-199.ngrok-free.app/Users"

        val tmp1: Map<String, String> = mapOf(
            "user_id" to user_id,
            "name" to name,
            "group" to group
        )

        val bodyJson: String = JSONObject(tmp1).toString()
        Log.d("DEBUG", tmp1.toString())
        val header: HashMap<String, String> = hashMapOf("Content-Type" to "application/json")

        Fuel.post(url).body(bodyJson).header(header).responseJson{ request, response, result ->

            when(result){
                is Result.Failure -> {
                    Log.d("DEBUG", "addUser failed")
                }

                is Result.Success -> {
                    Log.d("DEBUG", "addUser successed")
                    val tmp = Json.decodeFromString<UserRepositories>(result.get().obj().toString())
                    _repositories.value = tmp

                }
            }

        }



    }
}