package com.example.mediatorpattern.ui.theme

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mediatorpattern.ChatRoom
import com.example.mediatorpattern.User

class ChatViewModel : ViewModel() {
    private val chatMediator = ChatRoom()

    val users = mutableListOf<User>()
    val messages = MutableLiveData<List<String>>(emptyList())

    init {
        users.add(User("Александра", chatMediator))
        users.add(User("Паша", chatMediator))
        users.add(User("Миша", chatMediator))
        users.add(User("Наташа", chatMediator))

        // Регистрируем юзеров в чат-комнате
        users.forEach { chatMediator.addUser(it) }
    }

    fun sendMessage(fromIndex: Int, toIndex: Int?, message: String) {
        val from = users[fromIndex]
        val to = toIndex?.let { users[it] }

        from.sendMessage(to, message)

        // Добавляем сообщение в историю
        val log = if (to == null) {
            "${from.name} -> All: $message"
        } else {
            "${from.name} -> ${to.name}: $message"
        }
        messages.value = messages.value.orEmpty() + log
    }
}
