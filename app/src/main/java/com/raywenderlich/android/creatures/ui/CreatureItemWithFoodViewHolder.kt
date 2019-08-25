package com.raywenderlich.android.creatures.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.view.LayoutInflater
import android.view.ViewGroup
import com.raywenderlich.android.creatures.databinding.ListItemCreatureWithFoodBinding
import com.raywenderlich.android.creatures.model.Creature
import com.raywenderlich.android.creatures.model.CreatureStore

/**
 * Created by Belema Ogan on 2019-08-25.
 */
class CreatureItemWithFoodViewHolder(
        private val creatureWithFoodBinding: ListItemCreatureWithFoodBinding) :
        RecyclerView.ViewHolder(creatureWithFoodBinding.root) {

    private val foodRecyclerViewAdapter = FoodRecyclerViewAdapter()

    private val viewPool = RecyclerView.RecycledViewPool()

    fun bind(creature: Creature, clickListener: (creature: Creature) -> Unit){
        creatureWithFoodBinding.creature = creature
        creatureWithFoodBinding.root.setOnClickListener {
            clickListener(creature)
        }
        creatureWithFoodBinding.foodRecyclerView.apply {
            layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL, false)
            adapter = foodRecyclerViewAdapter
            recycledViewPool = viewPool
        }
        foodRecyclerViewAdapter.submitList(CreatureStore.getCreatureFoods(creature))
    }

    companion object {
        fun from(parent: ViewGroup): CreatureItemWithFoodViewHolder {
            val creatureWithFoodBinding = ListItemCreatureWithFoodBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            LinearSnapHelper().attachToRecyclerView(creatureWithFoodBinding.foodRecyclerView)
            return CreatureItemWithFoodViewHolder(creatureWithFoodBinding)
        }
    }
}