package com.example.mediatorpattern

class User(val name: String, private val mediator: ChatMediator) {
    fun sendMessage(to: User?, message: String) {
        println("$name отправляет сообщение: $message")
        mediator.sendMessage(this, to, message)
    }

    fun receiveMessage(from: User, message: String) {
        println("$name получил сообщение от ${from.name}: $message")
    }
}
