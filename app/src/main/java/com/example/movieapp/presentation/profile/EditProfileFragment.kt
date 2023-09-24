package com.example.movieapp.presentation.profile


import android.content.Intent
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.databinding.FragmentEditProfileBinding
import de.hdodenhof.circleimageview.CircleImageView


class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>(FragmentEditProfileBinding::inflate) {


    override fun onViewCreateFinish() {
        binding.cardView.setOnClickListener {
            with(binding){
                uploadImage(imageViewCircleImage)
            }

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1){
            binding.imageViewCircleImage.setImageURI(data?.data)
        }
    }

    //select and upload image
    private fun uploadImage(imageViewCircleImage: CircleImageView?) {
        binding.cardView.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

    }

    override fun observeEvents() {

    }

}