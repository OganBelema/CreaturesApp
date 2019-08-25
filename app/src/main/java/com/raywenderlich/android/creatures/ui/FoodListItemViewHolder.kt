package com.raywenderlich.android.creatures.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.raywenderlich.android.creatures.databinding.ListItemFoodBinding
import com.raywenderlich.android.creatures.model.Food

/**
 * Created by Belema Ogan on 2019-08-25.
 */
class FoodListItemViewHolder(private val listItemFoodBinding: ListItemFoodBinding) :
        RecyclerView.ViewHolder(listItemFoodBinding.root) {

    fun bind(food: Food){
        listItemFoodBinding.food = food
    }

    companion object {
        fun from(parent: ViewGroup): FoodListItemViewHolder {
            return FoodListItemViewHolder(ListItemFoodBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }
}