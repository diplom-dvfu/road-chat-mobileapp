package com.example.roadchat.data.model.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roadchat.R
import com.example.roadchat.data.model.db.ChatsAdapter.ChatViewHolder

class ChatsAdapter : ListAdapter<Chat, ChatViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.username, current.lastMessage, current.lastMessageDate)
    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val usernameItemView: TextView = itemView.findViewById(R.id.username_textview)
        private val lastMessageTextItemView: TextView =
            itemView.findViewById(R.id.last_message_textview)
        private val lastMessageDateItemView: TextView =
            itemView.findViewById(R.id.date_last_message_textview)

        fun bind(username: String?, lastMessageText: String?, lastMessageDateText: String?) {
            usernameItemView.text = username
            lastMessageTextItemView.text = lastMessageText
            lastMessageDateItemView.text = lastMessageDateText
        }

        companion object {
            fun create(parent: ViewGroup): ChatViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_preview_item, parent, false)
                return ChatViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Chat>() {
            override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return false
            }
        }
    }
}