package com.raywenderlich.android.creatures.ui

import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.databinding.CreatureItemCreatureCardJupiterBinding
import com.raywenderlich.android.creatures.model.Creature

/**
 * Created by Belema Ogan on 2019-08-25.
 */
class CreatureJupiterCardViewHolder(
        private val creatureItemCreatureCardJupiterBinding: CreatureItemCreatureCardJupiterBinding) :
        RecyclerView.ViewHolder(creatureItemCreatureCardJupiterBinding.root) {

    fun bind(creature: Creature, clickListener: (creature: Creature) -> Unit){
        creatureItemCreatureCardJupiterBinding.apply {
            this.creature = creature
            root.apply {
                setOnClickListener {
                    clickListener(creature)
                }
                val image = BitmapFactory.decodeResource(context.resources, context.resources
                        .getIdentifier(creature.thumbnail, null, context.packageName))
                Palette.from(image).generate {
                    val backgroundColor = it.getDominantColor(ContextCompat.getColor(context,
                            R.color.colorPrimaryDark))
                    creatureItemCreatureCardJupiterBinding.creatureCardView
                            .setCardBackgroundColor(backgroundColor)
                    creatureItemCreatureCardJupiterBinding.fullNameBackgroundView
                            .setBackgroundColor(backgroundColor)
                    val textColor = if (colorIsDark(backgroundColor)) Color.WHITE else Color.BLACK
                    creatureItemCreatureCardJupiterBinding.fullNameTextView.setTextColor(textColor)
                    creatureItemCreatureCardJupiterBinding.sloganTextView.setTextColor(textColor)
                }
                animateView(this)
            }
        }
    }

    private fun colorIsDark(backgroundColor: Int): Boolean {
        val darkness = 1 - (0.299 * Color.red(backgroundColor)
                + 0.587 * Color.green(backgroundColor) + 0.114 * Color.blue(backgroundColor)) / 255
        return darkness >= 0.5
    }

    private fun animateView(viewToAnimate: View){
        if (viewToAnimate.animation == null){
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.scale)
            viewToAnimate.animation = animation
        }
    }

    companion object {
        fun from(parent: ViewGroup): CreatureJupiterCardViewHolder {
            return CreatureJupiterCardViewHolder(CreatureItemCreatureCardJupiterBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }
}