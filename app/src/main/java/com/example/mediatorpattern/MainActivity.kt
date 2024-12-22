package com.example.mediatorpattern

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mediatorpattern.ui.theme.ChatViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        val spinnerFrom: Spinner = findViewById(R.id.spinnerFrom)
        val spinnerTo: Spinner = findViewById(R.id.spinnerTo)
        val editMessage: EditText = findViewById(R.id.editMessage)
        val buttonSend: Button = findViewById(R.id.buttonSend)
        val textMessages: TextView = findViewById(R.id.textMessages)

        val userNames = viewModel.users.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, userNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        viewModel.messages.observe(this) { messages ->
            textMessages.text = messages.joinToString("\n")
        }

        buttonSend.setOnClickListener {
            val fromIndex = spinnerFrom.selectedItemPosition
            val toIndex = spinnerTo.selectedItemPosition.takeIf { spinnerTo.selectedItemPosition != AdapterView.INVALID_POSITION }
            val message = editMessage.text.toString()

            if (message.isNotBlank()) {
                viewModel.sendMessage(fromIndex, toIndex, message)
                editMessage.text.clear()
            }
        }
    }
}
