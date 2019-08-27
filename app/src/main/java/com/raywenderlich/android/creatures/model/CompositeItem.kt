package com.raywenderlich.android.creatures.model

/**
 * Created by Belema Ogan on 2019-08-27.
 */
class CompositeItem {

    var creature: Creature? = null
        private set

    var header: Header? = null
        private set

    var isHeader = false
        private set

    companion object {
        fun withCreature(creature: Creature): CompositeItem {
            val compositeItem = CompositeItem()
            compositeItem.creature = creature
            return compositeItem
        }

        fun withHeader(header: Header): CompositeItem {
            val compositeItem = CompositeItem()
            compositeItem.header = header
            compositeItem.isHeader = true
            return compositeItem
        }
    }

    override fun toString(): String {
        return if (isHeader) header?.name ?: "" else creature?.nickname ?: ""
    }
}