package com.raywenderlich.android.creatures.ui

import android.support.v7.util.DiffUtil
import com.raywenderlich.android.creatures.model.Food

/**
 * Created by Belema Ogan on 2019-08-25.
 */
object FoodDiffUtil: DiffUtil.ItemCallback<Food>() {

    override fun areItemsTheSame(oldItem: Food?, newItem: Food?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: Food?, newItem: Food?): Boolean {
        return oldItem?.equals(newItem) ?: false
    }
}