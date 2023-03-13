package ru.cft.marketplace.util

import android.widget.ImageView
import android.widget.TextView

interface Category {
    fun setDetails(title: TextView, image: ImageView)

    fun clickReact()

    fun isSelected() : Boolean

    class Base(
        private val name: String,
        private val icon: Int,
        private var isSelected: Boolean
    ) : Category {

        override fun setDetails(title: TextView, image: ImageView) {
            title.text = name
            image.setImageResource(icon)
        }

        override fun clickReact() {
            isSelected = !isSelected
        }

        override fun isSelected() = isSelected
    }
}