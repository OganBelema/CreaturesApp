package com.raywenderlich.android.creatures.ui

import android.graphics.Canvas
import android.graphics.Paint
import android.support.v7.widget.RecyclerView

/**
 * Created by Belema Ogan on 2019-08-26.
 */
class DividerItemDecoration(color: Int, private val heightInPixel: Int) :
        RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = color
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDraw(canvas, parent, state)

        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount){
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + heightInPixel

            canvas?.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }
}