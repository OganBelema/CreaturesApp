package com.raywenderlich.android.creatures.ui

import android.support.v7.util.DiffUtil
import com.raywenderlich.android.creatures.model.Creature

/**
 * Created by Belema Ogan on 2019-08-24.
 */
object CreatureDiffUtil: DiffUtil.ItemCallback<Creature>() {

    override fun areItemsTheSame(oldItem: Creature?, newItem: Creature?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: Creature?, newItem: Creature?): Boolean {
        return oldItem?.equals(newItem) ?: false
    }
}