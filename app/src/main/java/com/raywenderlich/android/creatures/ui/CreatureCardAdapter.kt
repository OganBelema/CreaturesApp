package com.raywenderlich.android.creatures.ui

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.raywenderlich.android.creatures.Constants
import com.raywenderlich.android.creatures.model.Creature
import java.lang.IllegalArgumentException

/**
 * Created by Belema Ogan on 2019-08-24.
 */
class CreatureCardAdapter(private val clickListener: (creature: Creature) -> Unit) :
        ListAdapter<Creature, RecyclerView.ViewHolder>(CreatureDiffUtil) {

    lateinit var creatureCardViewHolder: CreatureCardViewHolder

    var jupiterSpanSize = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            ViewType.JUPITER.ordinal -> CreatureJupiterCardViewHolder.from(parent)
            ViewType.OTHER.ordinal -> CreatureCardViewHolder.from(parent)
            ViewType.MARS.ordinal -> CreatureMarsCardViewHolder.from(parent)
            else -> throw IllegalArgumentException("Unknown view type")
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).planet) {
            Constants.JUPITER -> ViewType.JUPITER.ordinal
            Constants.MARS -> ViewType.MARS.ordinal
            else -> ViewType.OTHER.ordinal
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CreatureCardViewHolder -> {
                creatureCardViewHolder = holder
                holder.bind(getItem(position), clickListener)
            }

            is CreatureJupiterCardViewHolder -> {
                holder.bind(getItem(position), clickListener)
            }

            is CreatureMarsCardViewHolder -> {
                holder.bind(getItem(position), clickListener)
            }
        }

    }

    fun spanSizeAtPosition(position: Int): Int {
        return if (getItem(position).planet == Constants.JUPITER) {
            jupiterSpanSize
        } else {
            1
        }
    }
}