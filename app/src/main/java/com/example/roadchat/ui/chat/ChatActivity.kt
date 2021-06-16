package com.example.roadchat.ui.chat

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roadchat.R
import com.example.roadchat.RoadChatApplication
import com.example.roadchat.ui.chats_preview.ChatsPreviewActivity
import com.example.roadchat.ui.setImageColor

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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val position = this.intent.getIntExtra("pos", 0)
        val username = this.intent.getStringExtra("accountName")

        findViewById<TextView>(R.id.chat_username_textview).text = username

        if (username != null) {
            if (username.matches(Regex("^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}\$"))) {
                findViewById<ImageView>(R.id.chat_avatar_chat).setImageResource(R.drawable.ic_car)
            } else {
                findViewById<ImageView>(R.id.chat_avatar_chat).setImageResource(R.drawable.ic_account)
            }
            setImageColor(this, findViewById(R.id.chat_avatar_chat), position)
        }

        findViewById<ImageView>(R.id.return_imageview).setOnClickListener {
            startActivity(Intent(this, ChatsPreviewActivity::class.java))
        }


        initChatsPreviewView()
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

        findViewById<ImageView>(R.id.sendBtn).setOnClickListener {
            Toast.makeText(this, "Нет соединения с сервером", Toast.LENGTH_SHORT).show()
        }

        chatViewModel.messages.observe(owner = this) { chatsPreview ->
            chatsPreview.let {
                adapter.submitList(it)
                recyclerView.scrollToPosition(chatsPreview.size - 1)
            }
        }
    }
}