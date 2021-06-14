package com.example.roadchat.ui

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.roadchat.R


fun setImageColor(context: Context, view: ImageView, position: Int) {
    val colors = listOf(R.color.green, R.color.blue, R.color.purple)
    view.setColorFilter(
        ContextCompat.getColor(context, colors[position % colors.size]),
        android.graphics.PorterDuff.Mode.SRC_IN
    );
}