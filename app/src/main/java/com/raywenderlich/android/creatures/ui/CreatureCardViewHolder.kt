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
import com.raywenderlich.android.creatures.databinding.CreatureItemCreatureCardBinding
import com.raywenderlich.android.creatures.model.Creature

/**
 * Created by Belema Ogan on 2019-08-25.
 */
class CreatureCardViewHolder(
        private val creatureItemCreatureCardBinding: CreatureItemCreatureCardBinding) :
        RecyclerView.ViewHolder(creatureItemCreatureCardBinding.root) {

    var scrollDirection = ScrollDirection.DOWN

    fun bind(creature: Creature, clickListener: (creature: Creature) -> Unit){
        creatureItemCreatureCardBinding.apply {
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
                    creatureItemCreatureCardBinding.creatureCardView
                            .setCardBackgroundColor(backgroundColor)
                    creatureItemCreatureCardBinding.fullNameBackgroundView
                            .setBackgroundColor(backgroundColor)
                    val textColor = if (colorIsDark(backgroundColor)) Color.WHITE else Color.BLACK
                    creatureItemCreatureCardBinding.fullNameTextView.setTextColor(textColor)
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
            val animId = if (scrollDirection == ScrollDirection.DOWN) R.anim.slide_from_bottom else
                R.anim.slide_from_top
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, animId)
            viewToAnimate.animation = animation
        }
    }

    companion object {
        fun from(parent: ViewGroup): CreatureCardViewHolder {
            return CreatureCardViewHolder(CreatureItemCreatureCardBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }
}