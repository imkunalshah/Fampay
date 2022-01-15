package com.kunal.fampay.utils

import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.kunal.fampay.data.network.models.FormattedText

class TextFormatter {
    companion object {
        fun applyFormattedText(
            formattedText: FormattedText?,
            view: TextView,
            text: String?
        ) {

            if (formattedText == null) {
                view.text = text
            } else {
                val spannableStringBuilder = SpannableStringBuilder()
                for (index in formattedText.entities.indices) {
                    val entity = formattedText.entities[index]
                    val colorCode = Color.parseColor(formattedText.entities[index].color)
                    var spannableString = SpannableString("${entity.text} ")

                    spannableString.setSpan(
                        ForegroundColorSpan(colorCode),
                        0,
                        entity.text.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spannableStringBuilder.append(spannableString)
                }
                if (spannableStringBuilder.isEmpty()) {
                    view.text = text
                } else {
                    view.text = spannableStringBuilder
                }
            }
        }
    }
}