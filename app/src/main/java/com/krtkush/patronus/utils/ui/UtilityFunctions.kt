package com.krtkush.patronus.utils.ui

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.krtkush.patronus.R

class UtilityFunctions {

    companion object {

        fun TextView.setTagBgColor(text: String) {

            if (text == "Ban") {
                this.setBackgroundColor(ContextCompat.getColor(this.context, R.color.tag_pink))
            } else if (text == "Fam") {
                this.setBackgroundColor(ContextCompat.getColor(this.context, R.color.background_light_grey))
            }
        }
    }
}