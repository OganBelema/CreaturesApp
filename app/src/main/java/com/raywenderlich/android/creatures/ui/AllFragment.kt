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
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.*
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.databinding.FragmentAllBinding
import com.raywenderlich.android.creatures.model.Creature
import com.raywenderlich.android.creatures.model.CreatureStore


class AllFragment : Fragment() {

  companion object {
    fun newInstance(): AllFragment {
      return AllFragment()
    }
  }

  private val clickListener: (creature: Creature) -> Unit = {creature ->
    context?.let {
      startActivity(CreatureActivity.newIntent(it, creature.id))
    }
  }

  private lateinit var fragmentAllBinding: FragmentAllBinding

  private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

  private lateinit var listItemDecoration: RecyclerView.ItemDecoration

  private lateinit var gridItemDecoration: RecyclerView.ItemDecoration

  private lateinit var listMenuItem: MenuItem

  private lateinit var gridMenuItem: MenuItem

  private var gridState = GridState.GRID

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

    fragmentAllBinding = FragmentAllBinding.inflate(layoutInflater)

    val creatureAdapter =  CreatureCardAdapter(clickListener)

    staggeredGridLayoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)

    val spacingInPixels = resources.getDimensionPixelSize(R.dimen.creature_card_grid_layout_margin)

    listItemDecoration = SpacingItemDecoration(1, spacingInPixels)

    gridItemDecoration = SpacingItemDecoration(2, spacingInPixels)

    fragmentAllBinding.creatureRecyclerView.apply {
      adapter = creatureAdapter
      this.layoutManager = staggeredGridLayoutManager
      addItemDecoration(gridItemDecoration)
    }
    creatureAdapter.submitList(CreatureStore.getCreatures())

    setHasOptionsMenu(true)

    return fragmentAllBinding.root
  }

  override fun onPrepareOptionsMenu(menu: Menu) {
    super.onPrepareOptionsMenu(menu)
    listMenuItem = menu.findItem(R.id.actionSpan1)
    gridMenuItem = menu.findItem(R.id.actionSpan2)

    when(gridState){
      GridState.LIST -> {
        listMenuItem.isEnabled = false
        gridMenuItem.isEnabled = true
      }

      GridState.GRID -> {
        gridMenuItem.isEnabled = false
        listMenuItem.isEnabled = true
      }
    }
  }


  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater?.inflate(R.menu.main, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when(item?.itemId){
      R.id.actionSpan1 -> {
        gridState = GridState.LIST
        updateRecyclerView(1, listItemDecoration, gridItemDecoration)
      }

      R.id.actionSpan2 -> {
        gridState = GridState.GRID
        updateRecyclerView(2, gridItemDecoration, listItemDecoration)
      }
    }
    return true
  }

  private fun updateRecyclerView(spanCount: Int, addItemDecoration: RecyclerView.ItemDecoration,
                                 removeItemDecoration: RecyclerView.ItemDecoration) {
    staggeredGridLayoutManager.spanCount = spanCount

    fragmentAllBinding.creatureRecyclerView.apply {
      removeItemDecoration(removeItemDecoration)
      addItemDecoration(addItemDecoration)
    }

  }

}