package com.obvious.galleryapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.obviousgalleryapp.model.ImageResponseItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.obvious.galleryapp.R
import com.obvious.galleryapp.Utils

class BottomSheetDialogFragment : BottomSheetDialogFragment() {

    lateinit var imageData: ImageResponseItem
    lateinit var tvTitle: TextView
    lateinit var tvDate: TextView
    lateinit var tvDetails: TextView
    lateinit var tvCopyright: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_bottom_sheet, container, false)
        tvTitle = view.findViewById(R.id.tvTitleVal)
        tvDate = view.findViewById(R.id.tvDateVal)
        tvDetails = view.findViewById(R.id.tvDetailsVal)
        tvCopyright = view.findViewById(R.id.tvCopyrightVal)
        getData()
        return view
    }

    /**
     * method to get data from full screen image fragment to bottom sheet dialog
     */
    private fun getData() {
        imageData =
            Gson().fromJson(arguments?.getString("imageData"), ImageResponseItem::class.java)
        tvTitle.text = imageData.title
        tvDate.text = Utils.getParsedDate(imageData.date!!)
        tvDetails.text = imageData.explanation
        tvCopyright.text = imageData.copyright
    }


}