package com.raywenderlich.android.creatures.ui

import android.graphics.Canvas
import android.graphics.Paint
import android.support.v7.widget.RecyclerView

/**
 * Created by Belema Ogan on 2019-08-26.
 */
class FoodItemDividerDecoration(color: Int, private val widthInPixel: Int) :
        RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = color
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDraw(canvas, parent, state)

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            // Top divider
            var left = parent.paddingLeft
            var right = parent.width - parent.paddingRight
            var top = child.top + params.topMargin
            var bottom = top + widthInPixel

            canvas?.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)

            // Right divider
            left = child.right - params.rightMargin
            right = left + widthInPixel
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas?.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }

}