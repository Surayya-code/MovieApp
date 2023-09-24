package com.example.movieapp.presentation.mylList.adapter

import android.annotation.SuppressLint
import com.example.movieapp.data.local.db.MyListDb
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.common.utils.ImageTypeEnum
import com.example.movieapp.common.utils.loadUrl
import com.example.movieapp.databinding.ItemFavoriteBinding

class MyFavoriteAdapter:RecyclerView.Adapter<MyFavoriteAdapter.MyFavoriteViewHolder>() {

    inner class MyFavoriteViewHolder(val binding:ItemFavoriteBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(favorite: MyListDb) {
            with(binding){
                tvTitle.text = favorite.title
                ivMovie.loadUrl(favorite.image, ImageTypeEnum.MOVIE_IMAGE)
                tvRating.text=favorite.vote.toString()


                favoriteCard.setOnClickListener {
//                    Navigation.findNavController(it)
//                        .navigate(MyListFragmentDirections.todetailFragment(favorite.id.toString()))
                }
            }

        }


    }

    object diffUtilFavorite : DiffUtil.ItemCallback<MyListDb>() {
        override fun areItemsTheSame(oldItem: MyListDb, newItem: MyListDb): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyListDb, newItem: MyListDb): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtilFavorite)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyFavoriteViewHolder {
       val layout=ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyFavoriteViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }


    override fun onBindViewHolder(holder: MyFavoriteAdapter.MyFavoriteViewHolder, position: Int) {
      val myListMovie=differ.currentList[position]
        holder.bind(myListMovie)
        holder.binding.delete.setOnClickListener {
            onDeleteClick?.invoke(myListMovie)
        }
    }
  var onDeleteClick: ((MyListDb) -> Unit)? = null

}