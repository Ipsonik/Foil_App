package com.example.foliarz

import android.content.Context
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.cardview.widget.CardView

fun cardviewanimations(cardView: CardView, context: Context, left_or_right: String) {
    if (left_or_right == "left") {
        val animation = AnimationUtils.loadAnimation(context, R.anim.cva_left)
        cardView.startAnimation(animation)
    } else {
        val animation = AnimationUtils.loadAnimation(context, R.anim.cva_right)
        cardView.startAnimation(animation)
    }
}

fun layoutanimation(layout: LinearLayout, context: Context) {
    val animation = AnimationUtils.loadAnimation(context, R.anim.tvandiv_animations)
    layout.startAnimation(animation)
}
