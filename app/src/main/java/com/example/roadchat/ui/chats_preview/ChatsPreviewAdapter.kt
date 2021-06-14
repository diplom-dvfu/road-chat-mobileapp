package com.example.roadchat.ui.chats_preview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roadchat.R
import com.example.roadchat.data.db.entity.Chat
import com.example.roadchat.ui.chat.ChatActivity
import com.example.roadchat.ui.chats_preview.ChatsPreviewAdapter.ChatViewHolder
import com.example.roadchat.ui.setImageColor

class ChatsPreviewAdapter(private val context: Context) :
    ListAdapter<Chat, ChatViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder.create(parent, context)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.username, current.lastMessage, current.lastMessageDate, position)
        holder.itemView.setOnClickListener {
            val chatIntent = Intent(this.context, ChatActivity::class.java)
            chatIntent.putExtra("accountName", current.username)
            chatIntent.putExtra("interlocutorName", "ikhacha")
            chatIntent.putExtra("pos", position)
            this.context.startActivity(chatIntent)
//            Toast.makeText(this.context, current.username, Toast.LENGTH_SHORT).show()
        }
    }

    class ChatViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        private val avatarItemView: ImageView = itemView.findViewById(R.id.chat_avatar)
        private val usernameItemView: TextView = itemView.findViewById(R.id.username_textview)
        private val lastMessageTextItemView: TextView =
            itemView.findViewById(R.id.last_message_textview)
        private val lastMessageDateItemView: TextView =
            itemView.findViewById(R.id.date_last_message_textview)

        fun bind(
            username: String?,
            lastMessageText: String?,
            lastMessageDateText: String?,
            position: Int
        ) {
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
            setImageColor(this.context, avatarItemView, position)
        }

        companion object {
            fun create(parent: ViewGroup, context: Context): ChatViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_preview_item, parent, false)
                return ChatViewHolder(view, context)
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