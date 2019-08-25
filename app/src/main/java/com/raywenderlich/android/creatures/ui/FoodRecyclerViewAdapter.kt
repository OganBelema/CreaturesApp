package com.raywenderlich.android.creatures.ui

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.ViewGroup
import com.raywenderlich.android.creatures.model.Food

/**
 * Created by Belema Ogan on 2019-08-25.
 */
class FoodRecyclerViewAdapter: ListAdapter<Food, FoodListItemViewHolder>(FoodDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListItemViewHolder {
        return FoodListItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FoodListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}