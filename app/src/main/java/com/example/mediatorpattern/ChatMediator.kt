package com.example.mediatorpattern

interface ChatMediator {
    fun sendMessage(from: User, to: User?, message: String)
    fun addUser(user: User)
}