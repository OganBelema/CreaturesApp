package com.raywenderlich.android.creatures.ui

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.ViewGroup
import com.raywenderlich.android.creatures.model.Creature

/**
 * Created by Belema Ogan on 2019-08-24.
 */
class CreatureCardAdapter(private val clickListener: (creature: Creature) -> Unit) :
        ListAdapter<Creature, CreatureCardViewHolder>(CreatureDiffUtil) {

    lateinit var creatureCardViewHolder: CreatureCardViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatureCardViewHolder {
        return CreatureCardViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CreatureCardViewHolder, position: Int) {
        creatureCardViewHolder = holder
        holder.bind(getItem(position), clickListener)
    }
}