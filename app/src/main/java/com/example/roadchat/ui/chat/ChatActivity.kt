package com.example.roadchat.ui.chat

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roadchat.R
import com.example.roadchat.RoadChatApplication

class ChatActivity : AppCompatActivity() {

    private val chatViewModel: ChatViewModel by viewModels {
        val accountName = this.intent.getStringExtra("accountName").toString()
        val interlocutorName = this.intent.getStringExtra("interlocutorName").toString()
        ChatViewModelFactory(
            accountName,
            interlocutorName,
            (application as RoadChatApplication).chatsRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val position = this.intent.getIntExtra("pos", 0)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initChatsPreviewView()

        findViewById<RecyclerView>(R.id.messagesRecyclerView).setOnClickListener {
            Toast.makeText(this, "HAHAHAA", Toast.LENGTH_SHORT)
            hideKeyboard()
        }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun initChatsPreviewView() {
        val recyclerView = findViewById<RecyclerView>(R.id.messagesRecyclerView)
        val adapter = MessageAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        chatViewModel.messages.observe(owner = this) { chatsPreview ->
            chatsPreview.let {
                adapter.submitList(it)
                recyclerView.scrollToPosition(chatsPreview.size - 1)
            }
        }
    }
}