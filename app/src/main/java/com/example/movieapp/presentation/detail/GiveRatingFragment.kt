package com.example.movieapp.presentation.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieapp.data.dto.DetailsResponseModelItem
import com.example.movieapp.databinding.FragmentGiveRatingBinding
import com.example.movieapp.domein.state.DetailUiState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.taufiqrahman.reviewratings.BarLabels
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random
@AndroidEntryPoint
class GiveRatingFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentGiveRatingBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<GiveRatingViewModel>()
    private val args by navArgs<GiveRatingFragmentArgs>()
    private lateinit var movie: DetailsResponseModelItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGiveRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData(args.id.toInt())

        val colors = intArrayOf(
            Color.parseColor("#0e9d58"),
            Color.parseColor("#bfd047"),
            Color.parseColor("#ffc105"),
            Color.parseColor("#ef7e14"),
            Color.parseColor("#d36259")
        )

        val raters = intArrayOf(
            Random.nextInt(100),
            Random.nextInt(100),
            Random.nextInt(100),
            Random.nextInt(100),
            Random.nextInt(100)
        )

        binding.ratingReview.createRatingBars(100, BarLabels.STYPE2,colors,raters)



        viewModel.detailMovies.observe(viewLifecycleOwner){
            when (it) {
                is DetailUiState.Success -> {
                    movie = it.data
                    val vote = movie.voteAverage
                    val formattedVote = String.format("%.1f", vote)
                    binding.Rating.text = formattedVote
                }

                is DetailUiState.Error -> {
                    Toast.makeText(context, "Error download details", Toast.LENGTH_SHORT).show()

                }

                is DetailUiState.Loading -> {

                }

            }
        }

        setButtons()

    }

    private fun getData(id: Int) {
        viewModel.getDetailMovie(id)
    }

    private fun setButtons(){
        binding.buttonCancel.setOnClickListener {
            dialog?.dismiss()
        }

        binding.buttonSubmit.setOnClickListener {
            Toast.makeText(context, "Your rating was Submitted", Toast.LENGTH_SHORT).show()
            dialog?.dismiss()
        }
    }
}

