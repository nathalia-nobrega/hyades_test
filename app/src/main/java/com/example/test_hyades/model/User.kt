package com.example.test_hyades.model

data class User (val name: String,
                 val email: String,
                 val password: String) {

    constructor(
    ) : this("No name", "example@email.com","00000")
}