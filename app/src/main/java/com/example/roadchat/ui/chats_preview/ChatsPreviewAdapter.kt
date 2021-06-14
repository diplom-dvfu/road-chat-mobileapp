package com.example.roadchat.ui.chats_preview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roadchat.R
import com.example.roadchat.data.db.entity.Chat
import com.example.roadchat.ui.chats_preview.ChatsPreviewAdapter.ChatViewHolder

class ChatsPreviewAdapter(private val context: Context) :
    ListAdapter<Chat, ChatViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.username, current.lastMessage, current.lastMessageDate)
        holder.itemView.setOnClickListener {
            Toast.makeText(this.context, current.username, Toast.LENGTH_SHORT).show()
        }
    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatarItemView: ImageView = itemView.findViewById(R.id.chat_avatar)
        private val usernameItemView: TextView = itemView.findViewById(R.id.username_textview)
        private val lastMessageTextItemView: TextView =
            itemView.findViewById(R.id.last_message_textview)
        private val lastMessageDateItemView: TextView =
            itemView.findViewById(R.id.date_last_message_textview)

        fun bind(username: String?, lastMessageText: String?, lastMessageDateText: String?) {
            usernameItemView.text = username
            lastMessageTextItemView.text = lastMessageText
            lastMessageDateItemView.text = lastMessageDateText
            if (username != null) {
                if (username.matches(Regex("^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}\$"))) {
                    avatarItemView.setImageResource(R.drawable.ic_car)
                } else {
                    avatarItemView.setImageResource(R.drawable.ic_account)
                }
            }
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