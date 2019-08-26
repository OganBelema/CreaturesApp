package com.raywenderlich.android.creatures.ui

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Belema Ogan on 2019-08-26.
 */
class SpacingItemDecoration(private val spanCount: Int, private val spacing: Int) :
        RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildAdapterPosition(view)

        outRect?.top = spacing / 2
        outRect?.bottom = spacing / 2
        outRect?.left = spacing / 2
        outRect?.right = spacing / 2

        // Adjust top edge
        if (position < spanCount){
            outRect?.top = spacing
        }

        // Adjust left edge
        if (position % spanCount == 0){
            outRect?.left = spacing
        }

        // Adjust right edge
        if((position + 1) % spanCount == 0){
            outRect?.right = spacing
        }
    }
}