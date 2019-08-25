package com.raywenderlich.android.creatures.ui

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.ViewGroup
import com.raywenderlich.android.creatures.model.Creature

/**
 * Created by Belema Ogan on 2019-08-25.
 */
class CreatureWithFoodRecyclerViewAdapter :
        ListAdapter<Creature, CreatureItemWithFoodViewHolder>(CreatureDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CreatureItemWithFoodViewHolder {
        return CreatureItemWithFoodViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CreatureItemWithFoodViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}