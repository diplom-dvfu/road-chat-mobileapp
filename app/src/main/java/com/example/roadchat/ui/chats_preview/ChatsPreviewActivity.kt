package com.example.roadchat.ui.chats_preview

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roadchat.R
import com.example.roadchat.RoadChatApplication
import com.example.roadchat.databinding.ActivityMainChatBinding
import com.example.roadchat.ui.all_accounts.AllAccountsActivity
import com.example.roadchat.ui.all_accounts.UserViewModel
import com.example.roadchat.ui.all_accounts.UserViewModelFactory
import com.example.roadchat.ui.setImageColor
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ChatsPreviewActivity : AppCompatActivity() {
    private val chatsPreviewViewModel: ChatsPreviewViewModel by viewModels {
        ChatsPreviewViewModelFactory((application as RoadChatApplication).chatsRepository)
    }

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as RoadChatApplication).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_chat)
        val binding = ActivityMainChatBinding.inflate(layoutInflater)
        initChatsPreviewView()
        initCallbacks()

        userViewModel.user.observe(this, Observer { user ->
            findViewById<TextView>(R.id.account_username_textview).text = user.username
            if (user.username.matches(Regex("^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}\$"))) {
                findViewById<ImageView>(R.id.account_avatar).setImageResource(R.drawable.ic_car)
            } else {
                findViewById<ImageView>(R.id.account_avatar).setImageResource(R.drawable.ic_account)
            }
            setImageColor(this, findViewById<ImageView>(R.id.account_avatar), user.userId)
        })
    }

    fun initCallbacks() {
        findViewById<ImageView>(R.id.account_avatar).setOnClickListener {
            startActivity(Intent(this, AllAccountsActivity::class.java))
        }
        findViewById<TextView>(R.id.account_username_textview).setOnClickListener {
            startActivity(Intent(this, AllAccountsActivity::class.java))
        }
    }

    fun initChatsPreviewView() {
        val recyclerView = findViewById<RecyclerView>(R.id.chats_preview_recycleview)
        val adapter = ChatsPreviewAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        chatsPreviewViewModel.allChats.observe(owner = this) { chatsPreview ->
            chatsPreview.let {
                adapter.submitList(it)
            }
        }

        findViewById<FloatingActionButton>(R.id.add_new_chat).setOnClickListener {
            Toast.makeText(this, "Нет соединения с сервером", Toast.LENGTH_LONG).show()
        }
    }


}
