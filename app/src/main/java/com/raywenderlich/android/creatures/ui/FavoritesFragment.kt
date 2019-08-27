/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.creatures.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.databinding.FragmentFavoritesBinding
import com.raywenderlich.android.creatures.model.CompositeItem
import com.raywenderlich.android.creatures.model.Creature
import com.raywenderlich.android.creatures.model.CreatureStore
import com.raywenderlich.android.creatures.model.Favorites
import java.util.*


class FavoritesFragment : Fragment(), ItemTouchHelperListener  {

    companion object {
        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }

    private val clickListener: (creature: Creature) -> Unit = { creature ->
        context?.let {
            startActivity(CreatureActivity.newIntent(it, creature.id))
        }
    }

    private lateinit var creatureAdapter: CreatureAdapter

    private lateinit var compositeItems: List<CompositeItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val fragmentFavoritesBinding: FragmentFavoritesBinding = FragmentFavoritesBinding.inflate(inflater)

        creatureAdapter = CreatureAdapter(clickListener)

        val heightInPixel = resources.getDimensionPixelSize(R.dimen.list_item_divider_height)

        fragmentFavoritesBinding.favoriteCreatureRecyclerView.apply {
            adapter = creatureAdapter
            addItemDecoration(DividerItemDecoration(ContextCompat.getColor(context, R.color.black),
                    heightInPixel))
            val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(this@FavoritesFragment))
            itemTouchHelper.attachToRecyclerView(this)
        }

        return fragmentFavoritesBinding.root
    }

    override fun onResume() {
        super.onResume()
        context?.let {
            compositeItems = CreatureStore.getFavoriteComposite(it)
            creatureAdapter.submitList(compositeItems)
        }
    }

    override fun onItemMove(recyclerView: RecyclerView, fromPosition: Int, toPosition: Int):
            Boolean {
        if (fromPosition < toPosition){
            for(i in fromPosition until toPosition){
                context?.let {
                    Collections.swap(compositeItems, i, i + 1)
                }
            }
        } else {
            for (i in fromPosition downTo toPosition + 1){
                Collections.swap(compositeItems, i, i - 1)
            }
        }
        creatureAdapter.notifyItemMoved(fromPosition, toPosition)
        return true
    }
}