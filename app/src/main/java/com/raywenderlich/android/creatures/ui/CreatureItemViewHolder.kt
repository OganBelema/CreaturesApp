package com.raywenderlich.android.creatures.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.databinding.ListItemCreatureBinding
import com.raywenderlich.android.creatures.model.Creature

/**
 * Created by Belema Ogan on 2019-08-24.
 */
class CreatureItemViewHolder(private val listItemCreatureBinding: ListItemCreatureBinding) :
        RecyclerView.ViewHolder(listItemCreatureBinding.root) {

    fun bind(creature: Creature?, clickListener: (creature: Creature) -> Unit,
             itemDragListener: (viewHolder: RecyclerView.ViewHolder) -> Unit) {
        creature?.let {
            listItemCreatureBinding.creature = creature
            listItemCreatureBinding.root.apply {
                setOnClickListener {
                    clickListener(creature)
                }
                animateView(this)
            }

            listItemCreatureBinding.dragHandle.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN){
                    itemDragListener(this)
                }
                false
            }
        }

    }

    private fun animateView(viewToAnimate: View) {
        if (viewToAnimate.animation == null) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.scale)
            viewToAnimate.animation = animation
        }
    }

    companion object {
        fun from(parent: ViewGroup): CreatureItemViewHolder {
            return CreatureItemViewHolder(ListItemCreatureBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }
}