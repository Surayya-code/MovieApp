package com.example.movieapp.presentation.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.common.utils.DiffUtilCallback
import com.example.movieapp.data.dto.DetailsResponseModelItem
import com.example.movieapp.databinding.ItemExploreBinding
import com.example.movieapp.databinding.ItemSearchBinding
import com.example.movieapp.presentation.explore.ExploreFragmentDirections

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.MovieHolder>(){

        inner class MovieHolder(val binding: ItemSearchBinding)
            : RecyclerView.ViewHolder(binding.root){
            fun bind(item: DetailsResponseModelItem) {
                with(binding){
                    movie = item
                    executePendingBindings()
                    cardViewSearch.setOnClickListener {
                        Navigation.findNavController(it).navigate(ExploreFragmentDirections.toDetailFragment(item.id.toString()))
                    }
                }
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
            val view = ItemSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return MovieHolder(view)
        }

        override fun getItemCount(): Int {
            return differ.currentList.size
        }

        override fun onBindViewHolder(holder: MovieHolder, position: Int) {
            val item = differ.currentList[position]
            holder.bind(item)
        }


        val differ = AsyncListDiffer(this, DiffUtilCallback)
}