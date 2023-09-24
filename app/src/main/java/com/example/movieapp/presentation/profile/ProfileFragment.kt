package com.example.movieapp.presentation.profile


import android.app.AlertDialog
import com.example.movieapp.common.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.example.movieapp.databinding.FragmentProfilBinding
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : BaseFragment<FragmentProfilBinding>(FragmentProfilBinding::inflate){
    private lateinit var auth : FirebaseAuth

    override fun onViewCreateFinish() {
            with(binding){
                constraintLayoutJoinPremium.setOnClickListener {
                 findNavController().navigate(ProfileFragmentDirections.toSubscribe())
                }
                clProfile.setOnClickListener {
                   findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment())
                }
            }

           logOut()

    }

    //exit from system
    private fun logOut(){
        binding.constLogout.setOnClickListener {
            val alertDialog = AlertDialog.Builder(requireContext()).apply {
                setTitle("Exit")
                setMessage("Do you want to exit?")
                setNegativeButton("No"){dialog,_ ->
                    dialog.dismiss()
                }
                setPositiveButton("Yes"){dialog,_->
                    auth= FirebaseAuth.getInstance()
                    auth.signOut()
                    findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSignInFragment())
                    dialog.dismiss()
                }
            }
            alertDialog.create()
            alertDialog.show()
        }
      //select and upload image
    binding.cardView.setOnClickListener {
        with(binding){
            uploadImage(imageViewCircleImage )
        }

      }
    }

    private fun uploadImage(imageViewProfile: CircleImageView?) {
        val intent = Intent()
        intent.action=Intent.ACTION_GET_CONTENT
        intent.type ="image/*"
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1){
            binding.imageViewCircleImage.setImageURI(data?.data)
        }
    }

    override fun observeEvents() {

    }

}