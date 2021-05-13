package com.tibaes.search.presentation.adapter

import com.tibaes.search.domain.entity.Item

interface ItemDetailListener {
    fun navToItemDetails(item: Item)
}