package com.example.movieapp.presentation.seeAllMovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.common.utils.DiffUtilCallback
import com.example.movieapp.data.dto.DetailsResponseModelItem
import com.example.movieapp.databinding.ItemSeeAllBinding
import com.example.movieapp.presentation.seeAllMovies.SeeAllMoviesFragmentDirections


class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieHolder>(){


    inner class MovieHolder(val binding: ItemSeeAllBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: DetailsResponseModelItem) {
            with(binding){
                movie = item
                executePendingBindings()
                seeAll.setOnClickListener {
                    Navigation.findNavController(it)
                        .navigate(SeeAllMoviesFragmentDirections.todetailFragment(item.id.toString()))
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = ItemSeeAllBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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