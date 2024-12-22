package com.example.mediatorpattern

class ChatRoom : ChatMediator {
    private val users = mutableListOf<User>()

    override fun sendMessage(from: User, to: User?, message: String) {
        if (to == null) {
            users.forEach { user ->
                if (user != from) user.receiveMessage(from, message)
            }
        } else {
            to.receiveMessage(from, message)
        }
    }

    override fun addUser(user: User) {
        users.add(user)
    }
}