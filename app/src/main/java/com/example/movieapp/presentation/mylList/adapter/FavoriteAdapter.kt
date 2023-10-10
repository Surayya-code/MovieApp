package com.example.movieapp.presentation.mylList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.local.db.FavoriteDTO
import com.example.movieapp.databinding.ItemFavoriteBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    var onDelete: (FavoriteDTO) -> Unit = {}
    var onClick: (Int) -> Unit = {}

    inner class FavoriteViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoriteDTO) {
            with(binding){
                movie = item
                tvRating.text=item.vote.toString()
                executePendingBindings()
            }

            binding.delete.setOnClickListener {
                onDelete(item)
            }
            itemView.setOnClickListener {
                onClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    object FavoriteDiffUtilCallback : DiffUtil.ItemCallback<FavoriteDTO>() {
        override fun areItemsTheSame(oldItem: FavoriteDTO, newItem: FavoriteDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FavoriteDTO,
            newItem: FavoriteDTO
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, FavoriteDiffUtilCallback)
}