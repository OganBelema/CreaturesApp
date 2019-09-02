package com.raywenderlich.android.creatures.ui

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

/**
 * Created by Belema Ogan on 2019-08-27.
 */
class ItemTouchHelperCallback(private val listener: ItemTouchHelperListener) :
        ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean = false

    override fun getMovementFlags(recyclerView: RecyclerView?,
                                  viewHolder: RecyclerView.ViewHolder?): Int {
        return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder): Boolean {
        return listener.onItemMove(recyclerView, viewHolder.adapterPosition, target.adapterPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            if (viewHolder is ItemSelectedListener){
                viewHolder.onItemSelected()
            }
        }

        super.onSelectedChanged(viewHolder, actionState)

    }

    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
        super.clearView(recyclerView, viewHolder)
        if (viewHolder is ItemSelectedListener){
            viewHolder.onItemCleared()
        }
    }
}