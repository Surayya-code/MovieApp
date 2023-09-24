package com.example.movieapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.common.utils.DiffUtilCallback
import com.example.movieapp.data.dto.DetailsResponseModelItem
import com.example.movieapp.databinding.ItemNewReleaseBinding
import com.example.movieapp.presentation.home.HomeFragmentDirections


class NewReleaseAdapter: RecyclerView.Adapter<NewReleaseAdapter.MovieHolder>() {

    inner class MovieHolder(val binding: ItemNewReleaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DetailsResponseModelItem) {
            with(binding) {
                topMovies = item
                executePendingBindings()
                newRelease.setOnClickListener {
                    Navigation.findNavController(it)
                        .navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(item.id.toString()))

                }
            }

        }

    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
         val view = ItemNewReleaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

