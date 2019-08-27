package com.raywenderlich.android.creatures.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.raywenderlich.android.creatures.databinding.ListItemPlanetHeaderBinding
import com.raywenderlich.android.creatures.model.Header

/**
 * Created by Belema Ogan on 2019-08-27.
 */
class HeaderViewHolder(private val listItemPlanetHeaderBinding: ListItemPlanetHeaderBinding) :
        RecyclerView.ViewHolder(listItemPlanetHeaderBinding.root) {

    fun bind(header: Header?) {
        header?.let {
            listItemPlanetHeaderBinding.header = header
        }
    }

    companion object {
        fun from(parent: ViewGroup): HeaderViewHolder {
            return HeaderViewHolder(ListItemPlanetHeaderBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }
}