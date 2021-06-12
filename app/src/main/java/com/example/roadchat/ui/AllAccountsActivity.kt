package com.example.roadchat.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.roadchat.R
import com.example.roadchat.ui.main_chat.MainChatActivity

class AllAccountsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_accounts)

        findViewById<ImageView>(R.id.close_imageview).setOnClickListener {
            startActivity(Intent(this, MainChatActivity::class.java))
        }
    }
}