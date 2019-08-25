package com.raywenderlich.android.creatures.ui

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.ViewGroup
import com.raywenderlich.android.creatures.model.Creature

/**
 * Created by Belema Ogan on 2019-08-24.
 */
class CreatureAdapter: ListAdapter<Creature, CreatureItemViewHolder>(CreatureDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatureItemViewHolder {
        return CreatureItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CreatureItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}