package com.example.roadchat.ui.edit_account

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roadchat.R
import com.example.roadchat.databinding.ActivityEditAccountBinding
import com.example.roadchat.ui.all_accounts.AllAccountsActivity
import com.example.roadchat.ui.login.LoginActivity
import com.example.roadchat.ui.setImageColor

class EditAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditAccountBinding
    private var isPersonalAccount: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAccountBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_edit_account)
        val accountName = this.intent.getStringExtra("accountName").toString()
        val position = this.intent.getIntExtra("pos", 0)
        findViewById<TextView>(R.id.account_username_editaccount).text = accountName
        if (accountName.matches(Regex("^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}\$"))) {
            isPersonalAccount = false
            findViewById<ImageView>(R.id.account_avatar_editaccount).setImageResource(R.drawable.ic_car)
        } else {
            isPersonalAccount = true
            findViewById<ImageView>(R.id.account_avatar_editaccount).setImageResource(R.drawable.ic_account)
        }
        setImageColor(this, findViewById<ImageView>(R.id.account_avatar_editaccount), position)


        findViewById<ImageView>(R.id.close_profile_imageview).setOnClickListener {
            startActivity(Intent(this, AllAccountsActivity::class.java))
        }

        initOnFocusListeners()
        initButtons()
    }


    fun initOnFocusListeners() {
        findViewById<EditText>(R.id.password_edittext_editaccount).setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard()
            }
        }

        binding.newPasswordConfirmEdittextEditaccount.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard()
            }
        }

        binding.newPasswordEdittextEditaccount.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard()
            }
        }
    }

    fun initButtons() {
        if (isPersonalAccount) {
            findViewById<TextView>(R.id.change_button_editaccount).text = "Изменить пароль"
            findViewById<TextView>(R.id.change_button_editaccount).setOnClickListener {
                Toast.makeText(this, "Нет соединения с сервером", Toast.LENGTH_LONG).show()
            }
            findViewById<TextView>(R.id.account_action_button_editaccount).text =
                "Выйти из аккаунта"
            findViewById<TextView>(R.id.account_action_button_editaccount).setOnClickListener {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        } else {
            findViewById<TextView>(R.id.change_button_editaccount).visibility = View.INVISIBLE
            findViewById<TextView>(R.id.password_edittext_editaccount).visibility = View.INVISIBLE
            findViewById<TextView>(R.id.new_password_edittext_editaccount).visibility =
                View.INVISIBLE
            findViewById<TextView>(R.id.new_password_confirm_edittext_editaccount).visibility =
                View.INVISIBLE
            findViewById<TextView>(R.id.account_action_button_editaccount).text = "Удалить машину"
            findViewById<TextView>(R.id.account_action_button_editaccount).setOnClickListener {
                Toast.makeText(this, "Нет соединения с сервером", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
//
//    fun View.hideKeyboard() {
//        val inputMethodManager =
//            context!!.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
//        inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
//    }
}