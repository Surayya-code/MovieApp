package com.example.movieapp.presentation.detail

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.movieapp.databinding.FragmentDownloadButtonBinding


class DownloadButtonFragment:  DialogFragment() {
    private  lateinit var binding: FragmentDownloadButtonBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    binding=FragmentDownloadButtonBinding.inflate(LayoutInflater.from(context))

        val  showDownloaderInfoDialogBuilder = AlertDialog.Builder(requireActivity())
        showDownloaderInfoDialogBuilder.setView(binding.root)
        val dialog =showDownloaderInfoDialogBuilder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnHide.setOnClickListener {
            dialog.dismiss()
        }
        return  dialog

}
}