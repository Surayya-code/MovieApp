package com.example.movieapp.common.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.data.dto.DetailsResponseModelItem

object DiffUtilCallback : DiffUtil.ItemCallback<DetailsResponseModelItem>() {
    override fun areItemsTheSame(
        oldItem: DetailsResponseModelItem,
        newItem: DetailsResponseModelItem,
    ): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DetailsResponseModelItem,
        newItem: DetailsResponseModelItem,
    ): Boolean {
        return oldItem==newItem
    }
}