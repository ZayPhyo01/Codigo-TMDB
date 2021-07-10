package com.codigo.tmdb.components.itemdecorators

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemGridItemDecorator(private val context : Context, private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val spaceIndp = context.resources.displayMetrics.density * space
        with(outRect) {
            right = spaceIndp.toInt()
            top = spaceIndp.toInt()
            bottom = spaceIndp.toInt()
            left = spaceIndp.toInt()
        }
    }
}