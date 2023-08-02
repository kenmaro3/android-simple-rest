package com.example.simplerest.Model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val user_id: String = "",
    val name: String = "",
    val group: String = "",
)


@Serializable
data class UserRepositories(
    val users: List<User>,
)

