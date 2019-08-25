package com.raywenderlich.android.creatures.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.raywenderlich.android.creatures.databinding.ListItemCreatureBinding
import com.raywenderlich.android.creatures.model.Creature

/**
 * Created by Belema Ogan on 2019-08-24.
 */
class CreatureItemViewHolder(private val listItemCreatureBinding: ListItemCreatureBinding) :
        RecyclerView.ViewHolder(listItemCreatureBinding.root) {

    fun bind(creature: Creature, clickListener: (creature: Creature) -> Unit){
        listItemCreatureBinding.creature = creature
        listItemCreatureBinding.root.setOnClickListener {
            clickListener(creature)
        }
    }

    companion object {
        fun from(parent: ViewGroup): CreatureItemViewHolder {
            return CreatureItemViewHolder(ListItemCreatureBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }
}