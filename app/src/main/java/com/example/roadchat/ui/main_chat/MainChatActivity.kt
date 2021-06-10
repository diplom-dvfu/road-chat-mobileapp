package com.example.roadchat.ui.main_chat

//import com.xwray.groupie.GroupAdapter
//import com.xwray.groupie.Item
//import com.xwray.groupie.ViewHolder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.roadchat.R
import com.example.roadchat.databinding.ActivityMainChatBinding

class MainChatActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainChatBinding
//    val adapter = GroupAdapter<ViewHolder>()
//    val latestMessagesMap = HashMap<String, ChatMessage>()
//    private fun refreshRecyclerViewMessages() {
////        val adapter = binding.recyclerviewLatestMessages.adapter
//        adapter.clear()
//        adapter.add(LatestMessageRow())
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_chat)
        val binding = ActivityMainChatBinding.inflate(layoutInflater)
//        binding.recyclerviewLatestMessages.adapter


    }
}