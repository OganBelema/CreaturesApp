package com.raywenderlich.android.creatures.ui
import android.databinding.BindingAdapter
import android.widget.ImageView

/**
 * Created by Belema Ogan on 2019-08-24.
 */
@BindingAdapter("creatureImage")
fun ImageView.loadImage(uri: String?){
    uri?.let {
        this.setImageResource(context.resources.getIdentifier(uri,
                null, context.packageName))
    }
}