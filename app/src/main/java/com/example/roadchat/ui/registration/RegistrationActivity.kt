package com.example.roadchat.ui.registration

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.roadchat.databinding.ActivityRegistrationBinding
import com.example.roadchat.ui.login.LoginActivity

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.isDriverCheckboxRegister.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.root.hideKeyboard()
            if (isChecked) {
                binding.plateEdittextRegister.visibility = View.VISIBLE
            } else {
                binding.plateEdittextRegister.visibility = View.INVISIBLE
            }
        }

        binding.usernameEdittextRegister.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                binding.root.hideKeyboard()
            }
        }

        binding.passwordEdittextRegister.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                binding.root.hideKeyboard()
            }
        }

        binding.passwordConfirmEdittextRegister.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                binding.root.hideKeyboard()
            }
        }

        binding.alreadyHaveAccountTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    fun View.hideKeyboard() {
        val inputMethodManager =
            context!!.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
    }
}