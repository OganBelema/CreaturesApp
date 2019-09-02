package com.raywenderlich.android.creatures.ui

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.raywenderlich.android.creatures.model.CompositeItem
import com.raywenderlich.android.creatures.model.Creature
import java.lang.IllegalArgumentException

/**
 * Created by Belema Ogan on 2019-08-24.
 */
class CreatureAdapter(private val clickListener: (creature: Creature) -> Unit,
                      private val itemDragListener: (viewHolder: RecyclerView.ViewHolder) -> Unit) :
        ListAdapter<CompositeItem, RecyclerView.ViewHolder>(CompositeDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.HEADER.ordinal -> HeaderViewHolder.from(parent)
            ViewType.CREATURE.ordinal -> CreatureItemViewHolder.from(parent)
            else -> throw IllegalArgumentException("Unknown View Type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isHeader)
            ViewType.HEADER.ordinal
        else
            ViewType.CREATURE.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(getItem(position).header)
            is CreatureItemViewHolder -> holder.bind(getItem(position).creature, clickListener,
                    itemDragListener)
        }
    }
}