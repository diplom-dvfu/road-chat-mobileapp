package com.example.roadchat.ui.all_accounts

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
import com.example.roadchat.data.db.entity.User
import com.example.roadchat.ui.chats_preview.ChatsPreviewActivity

class AccountsAdapter(private val context: Context) :
    ListAdapter<User, AccountsAdapter.AccountViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder.create(parent, context)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.username)
    }

    class AccountViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        private val avatarItemView: ImageView = itemView.findViewById(R.id.account_avatar_edit)
        private val usernameItemView: TextView =
            itemView.findViewById(R.id.account_name_textview_edit)
        private val changeItemView: ImageView = itemView.findViewById(R.id.account_edit)

        fun bind(username: String?) {
            usernameItemView.text = username
            if (username != null) {
                if (username.matches(Regex("^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}\$"))) {
                    avatarItemView.setImageResource(R.drawable.ic_car)
                } else {
                    avatarItemView.setImageResource(R.drawable.ic_account)
                }
            }
            changeItemView.setOnClickListener {
                this.context.startActivity(Intent(this.context, ChatsPreviewActivity::class.java))
            }
        }

        companion object {
            fun create(parent: ViewGroup, context: Context): AccountViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.account_item, parent, false)
                return AccountViewHolder(view, context)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return false
            }
        }
    }
}