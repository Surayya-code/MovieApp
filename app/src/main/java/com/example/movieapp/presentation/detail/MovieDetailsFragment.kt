package com.example.movieapp.presentation.detail

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.common.utils.gone
import com.example.movieapp.common.utils.visible
import com.example.movieapp.data.dto.DetailsResponseModelItem
import com.example.movieapp.data.local.db.MyListDb
import com.example.movieapp.databinding.FragmentMovieDetailsBinding
import com.example.movieapp.domein.state.CastUiState
import com.example.movieapp.domein.state.DetailUiState
import com.example.movieapp.presentation.detail.adapter.CastAdapter
import com.example.movieapp.presentation.detail.adapter.GenreAdapter
import com.example.movieapp.presentation.detail.adapter.ViewPagerTabLayoutAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding>(FragmentMovieDetailsBinding::inflate) {

    private val viewModel by viewModels<MovieDetailViewModel>()
    private val castAdapter = CastAdapter()
    private val genreAdapter = GenreAdapter()
    private val args by navArgs<MovieDetailsFragmentArgs>()
    private lateinit var movie: DetailsResponseModelItem


    override fun onViewCreateFinish() {

        sendToAnotherApp()
        showRating()
        showDialog()
        getData(args.id.toInt())
        setRecyclerViews()
        setViewPager()
        youtubePlay()
        saveMovie()
        back()


        }


    //goBack
    private fun back(){
        binding.imageViewBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    private fun getData(id: Int) {
        viewModel.getDetailMovie(id)
        viewModel.getCastDetail(id)
    }
   // saveMovie
   private fun saveMovie(){
       binding.movieSave.setOnClickListener {
           val movieToSave = binding.movieDetail

           if (movieToSave != null) {
               viewModel.addMyList(
                   MyListDb(
                       movieToSave.id,
                       movieToSave.title,
                       movieToSave.posterPath,
                       movieToSave.voteAverage
                   )
               )
               Toast.makeText(context, "${movieToSave.title} saved in movie list", Toast.LENGTH_SHORT).show()
           } else {
               Toast.makeText(context, "Error saving movie", Toast.LENGTH_SHORT).show()
           }


       }

       binding.imageViewBack.setOnClickListener {
           findNavController().navigateUp()
       }
   }




    //youtubePLay
    private fun youtubePlay(){
        binding.buttonPlay.setOnClickListener {
          // Navigation.findNavController(it).navigate(MovieDetailsFragmentDirections.actionToYoutubeFragment(movie.key))
        }
    }

    //DownloadButton
    private fun showDialog(){
        binding.buttonDownload.setOnClickListener {
            val dialogFragment= DownloadButtonFragment()
            dialogFragment.show(requireActivity().supportFragmentManager,"My Fragment")
        }
    }
    //showRating
    private fun showRating(){
        binding.constraintLayoutRating.setOnClickListener {
            findNavController().navigate(MovieDetailsFragmentDirections.toGiveRatingFragment(movie.id.toString()))
        }
    }

    //shateAnotherApp
    private fun sendToAnotherApp() {
        binding.imageViewSend.setOnClickListener {
            shareMovie()
        }
    }

    private fun shareMovie(){
        val sendIntent: Intent =Intent().apply{
            action=Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,"Sent to")
            type="text/plain"
        }
        startActivity(Intent.createChooser(sendIntent,null))
    }

   //tabLayout
   private fun setViewPager(){
       with(binding) {
           viewPagerTabLayout.adapter =
               ViewPagerTabLayoutAdapter(childFragmentManager, lifecycle, args.id.toInt())

           val tabsArray = arrayOf(
               "Trailers",
               "More Like This",
               "Comments",
           )

           TabLayoutMediator(tabLayout, viewPagerTabLayout) { tab, position ->
               tab.text = tabsArray[position]
           }.attach()
       }
   }

    //rv
    private fun setRecyclerViews() {
        binding.rvDetailGenre.adapter = genreAdapter
        binding.recyclerViewCast.adapter = castAdapter
    }

    override fun observeEvents() {
        viewModel.cast.observe(viewLifecycleOwner){
            when (it) {
                is CastUiState.Success -> {
                  castAdapter.differ.submitList(it.data.cast)
                }

                is CastUiState.Error -> {
                    Toast.makeText(context, "Error download cast", Toast.LENGTH_SHORT).show()
                }
                is CastUiState.Loading -> { }
            }
        }

        viewModel.detailMovies.observe(viewLifecycleOwner){
            when (it) {
                is DetailUiState.Success -> {
                    binding.movieDetail = it.data
                    genreAdapter.differ.submitList(it.data.genres)
                    movie = it.data
                    binding.progressBarDetail.gone()
                }

                is DetailUiState.Error -> {
                    Toast.makeText(context, "Error download details", Toast.LENGTH_SHORT).show()
                    binding.progressBarDetail.gone()
                }

                is DetailUiState.Loading -> {
                    binding.progressBarDetail.visible()
                }

            }
        }
    }

    }


