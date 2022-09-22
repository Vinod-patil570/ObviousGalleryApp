package com.example.obviousgalleryapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.obviousgalleryapp.model.ImageResponseItem
import com.example.obviousgalleryapp.ui.viewmodel.ImageViewModel
import com.google.gson.Gson
import com.obvious.galleryapp.adapter.FullScreenImageViewPagerAdapter
import com.obvious.galleryapp.databinding.FragmentFullscreenImageBinding
import com.obvious.galleryapp.ui.fragment.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [FullScreenImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class FullScreenImageFragment : Fragment() {

    lateinit var binding: FragmentFullscreenImageBinding
    lateinit var imageList: List<ImageResponseItem>
    lateinit var viewModel: ImageViewModel
    var selectedPosition: Int = 0
    lateinit var viewPagerAdapter: FullScreenImageViewPagerAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(requireActivity()).get(ImageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFullscreenImageBinding.inflate(inflater, container, false)
        getData()
        setupViewPager()
        addClickListeners()
        addViewPagerListener()
        return binding.root
    }


    /**
     * method to get data from previous fragment
     */
    private fun getData() {
        imageList = viewModel.getImageListData()
        selectedPosition = viewModel.getImagePositionData()
    }

    /**
     * method to setup viewpager with adapter
     */
    private fun setupViewPager() {
        viewPagerAdapter =
            FullScreenImageViewPagerAdapter(requireActivity(), imageList, selectedPosition)
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.setCurrentItem(selectedPosition, true)
    }

    /**
     * method to add all click listeners and handle clicks
     */
    private fun addClickListeners() {
        binding.tvShowPhotoDetails.setOnClickListener(View.OnClickListener {
            val dataJsonString = Gson().toJson(imageList.get(selectedPosition))
            val bundle = Bundle()
            bundle.putString("imageData", dataJsonString)
            val bottomSheetDialog = BottomSheetDialogFragment()
            bottomSheetDialog.arguments = bundle
            bottomSheetDialog.show(childFragmentManager, "bottom_sheet_dialog")
        })
    }

    /**
     * method to add viewpager page change listener
     * Need this listener to get current Image of viewpager
     */
    private fun addViewPagerListener() {
        binding.viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                selectedPosition = position
            }
        })
    }


}