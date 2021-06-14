package com.example.roadchat.ui.chat


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roadchat.R
import com.example.roadchat.data.db.entity.Message

class MessageAdapter(private val context: Context) :
    ListAdapter<Message, RecyclerView.ViewHolder>(WORDS_COMPARATOR) {

    private val holderTypeMessageReceived = 1
    private val holderTypeMessageSent = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            holderTypeMessageSent -> {
                SentMessageViewHolder.create(parent, context)
            }
            holderTypeMessageReceived -> {
                ReceivedMessageViewHolder.create(parent, context)
            }
            else -> {
                throw Exception("Error reading holder type")
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).username == getItem(position).senderName) {
            holderTypeMessageReceived
        } else {
            holderTypeMessageSent
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = getItem(position)
        when (holder.itemViewType) {
            holderTypeMessageSent -> (holder as SentMessageViewHolder).bind(
                current,
                position
            )
            holderTypeMessageReceived -> (holder as ReceivedMessageViewHolder).bind(
                current,
                position
            )
        }

    }

    class SentMessageViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        private val messageTextView: TextView = itemView.findViewById(R.id.messageText_sent)

        fun bind(
            messageInfo: Message,
            position: Int
        ) {
            messageTextView.text = messageInfo.text

        }

        companion object {
            fun create(parent: ViewGroup, context: Context): SentMessageViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.message_item_sent, parent, false)
                return SentMessageViewHolder(view, context)
            }
        }
    }

    class ReceivedMessageViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        private val messageTextView: TextView = itemView.findViewById(R.id.messageText_received)

        fun bind(
            messageInfo: Message,
            position: Int
        ) {
            messageTextView.text = messageInfo.text
        }

        companion object {
            fun create(parent: ViewGroup, context: Context): ReceivedMessageViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.message_item_received, parent, false)
                return ReceivedMessageViewHolder(view, context)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Message>() {
            override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
                return false
            }
        }
    }
}