package com.raywenderlich.android.creatures.ui

import android.support.v7.util.DiffUtil
import com.raywenderlich.android.creatures.model.CompositeItem

/**
 * Created by Belema Ogan on 2019-08-27.
 */
object CompositeDiffUtil: DiffUtil.ItemCallback<CompositeItem>() {
    override fun areItemsTheSame(oldItem: CompositeItem?, newItem: CompositeItem?): Boolean {
        return oldItem?.creature?.id == newItem?.creature?.id
    }

    override fun areContentsTheSame(oldItem: CompositeItem?, newItem: CompositeItem?): Boolean {
        return oldItem?.creature == newItem?.creature
    }
}