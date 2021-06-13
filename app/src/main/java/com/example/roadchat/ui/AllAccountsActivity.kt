package com.example.roadchat.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roadchat.R
import com.example.roadchat.RoadChatApplication
import com.example.roadchat.data.model.UserViewModel
import com.example.roadchat.data.model.UserViewModelFactory
import com.example.roadchat.data.model.db.AccountsAdapter
import com.example.roadchat.ui.main_chat.MainChatActivity

class AllAccountsActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as RoadChatApplication).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_accounts)

        findViewById<ImageView>(R.id.close_imageview).setOnClickListener {
            startActivity(Intent(this, MainChatActivity::class.java))
        }

        initAccountsView()
    }

    fun initAccountsView() {
        val recyclerView = findViewById<RecyclerView>(R.id.account_edit_recycleview)
        val adapter = AccountsAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        userViewModel.userAccounts.observe(owner = this) { chatsPreview ->
            chatsPreview.let {
                adapter.submitList(it)
            }
        }
    }
}