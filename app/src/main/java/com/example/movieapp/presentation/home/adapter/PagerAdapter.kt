package com.example.movieapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.common.utils.DiffUtilCallback
import com.example.movieapp.data.dto.DetailsResponseModelItem
import com.example.movieapp.databinding.ItemPagingBinding

class PagerAdapter : RecyclerView.Adapter<PagerAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(val binding: ItemPagingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: DetailsResponseModelItem) {
            with(binding) {
                topMovies=movie
                binding.executePendingBindings()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemPagingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.bind(movie)
    }


    val differ = AsyncListDiffer(this, DiffUtilCallback)


}