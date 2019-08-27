package com.raywenderlich.android.creatures.ui

import android.support.v7.widget.RecyclerView

/**
 * Created by Belema Ogan on 2019-08-27.
 */
interface ItemTouchHelperListener {

    fun onItemMove(recyclerView: RecyclerView, fromPosition: Int, toPosition: Int): Boolean
}