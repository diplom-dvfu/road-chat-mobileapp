package com.example.roadchat.ui.main_chat

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roadchat.R
import com.example.roadchat.RoadChatApplication
import com.example.roadchat.data.model.ChatsPreviewViewModel
import com.example.roadchat.data.model.ChatsPreviewViewModelFactory
import com.example.roadchat.data.model.db.ChatsAdapter
import com.example.roadchat.databinding.ActivityMainChatBinding


class MainChatActivity : AppCompatActivity() {
    private val chatsPreviewViewModel: ChatsPreviewViewModel by viewModels {
        ChatsPreviewViewModelFactory((application as RoadChatApplication).chatsRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_chat)
        val binding = ActivityMainChatBinding.inflate(layoutInflater)
        val recyclerView = findViewById<RecyclerView>(R.id.chats_preview_recycleview)
        val adapter = ChatsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        chatsPreviewViewModel.allChats.observe(owner = this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }
    }

}
