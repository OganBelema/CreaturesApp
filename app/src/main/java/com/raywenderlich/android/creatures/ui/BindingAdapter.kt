package com.raywenderlich.android.creatures.ui
import android.databinding.BindingAdapter
import android.widget.ImageButton
import android.widget.ImageView
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.model.Creature
import com.raywenderlich.android.creatures.model.Favorites

/**
 * Created by Belema Ogan on 2019-08-24.
 */
@BindingAdapter("creatureImage")
fun ImageView.loadCreatureImage(uri: String?){
    uri?.let {
        setImageResource(context.resources.getIdentifier(uri,
                null, context.packageName))
    }
}

@BindingAdapter("favoriteButtonImage")
fun ImageButton.setupFavoriteButtonImage(isFavorite: Boolean) {
    if (isFavorite) {
        setImageDrawable(context.getDrawable(R.drawable.ic_favorite_black_24dp))
    } else {
        setImageDrawable(context.getDrawable(R.drawable.ic_favorite_border_black_24dp))
    }
}

@BindingAdapter("favoriteButtonClickListener")
fun ImageButton.setupFavoriteButtonClickListener(creature: Creature) {
    this.setOnClickListener { _ ->
        if (creature.isFavorite) {
            setImageDrawable(context.getDrawable(R.drawable.ic_favorite_border_black_24dp))
            Favorites.removeFavorite(creature, context)
        } else {
            setImageDrawable(context.getDrawable(R.drawable.ic_favorite_black_24dp))
            Favorites.addFavorite(creature, context)
        }
    }
}

@BindingAdapter("foodImage")
fun ImageView.loadFoodImage(thumbnail: String?){
    thumbnail?.let {
        setImageResource(context.resources.getIdentifier(thumbnail,
                null, context.packageName))
    }
}