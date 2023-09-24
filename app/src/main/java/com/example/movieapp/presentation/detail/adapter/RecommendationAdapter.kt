package com.example.movieapp.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.dto.RecommendationResponse
import com.example.movieapp.databinding.ItemMoreLikeThisBinding

class RecommendationAdapter  : RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>(){

    inner class RecommendationViewHolder(private val binding: ItemMoreLikeThisBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecommendationResponse) {
            with(binding){
                recommendationdetail=item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val layout = ItemMoreLikeThisBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendationViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    object GenreDiffUtilCallback : DiffUtil.ItemCallback<RecommendationResponse>() {
        override fun areItemsTheSame(oldItem: RecommendationResponse, newItem: RecommendationResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecommendationResponse, newItem: RecommendationResponse): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, GenreDiffUtilCallback)
}