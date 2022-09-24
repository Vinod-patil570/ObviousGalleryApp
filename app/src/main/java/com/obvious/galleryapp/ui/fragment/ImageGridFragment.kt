package com.obvious.galleryapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.obviousgalleryapp.adapter.ImageGridAdapter
import com.example.obviousgalleryapp.model.ImageResponseItem
import com.example.obviousgalleryapp.ui.viewmodel.ImageViewModel
import com.obvious.galleryapp.R
import com.obvious.galleryapp.Utils
import com.obvious.galleryapp.databinding.FragmentImageGridBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [ImageGridFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ImageGridFragment : Fragment() {

    lateinit var viewModel: ImageViewModel
    lateinit var binding: FragmentImageGridBinding
    lateinit var gvAdapter: ImageGridAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImageGridBinding.inflate(inflater, container, false)
        checkInternet()
        return binding.root
    }

    /**
     * method to check internet and handle the flow accordingly
     */
    private fun checkInternet() {
        if (Utils.isOnline(requireActivity())) {
            binding.layoutNoInternet.root.visibility = View.GONE
            binding.tvMyCollection.visibility = View.VISIBLE
            binding.gvImages.visibility = View.VISIBLE
            getImages()
            subscribeImageObserver()
        } else {
            binding.layoutNoInternet.root.visibility = View.VISIBLE
            binding.tvMyCollection.visibility = View.GONE
            binding.gvImages.visibility = View.GONE
            binding.layoutNoInternet.btnRetry.setOnClickListener(View.OnClickListener {
                checkInternet()
            })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //get view model instance from view model store
        viewModel = ViewModelProvider(requireActivity()).get(ImageViewModel::class.java)
    }

    /**
     * method to get list of images from data.json file saved in assets folder
     */
    private fun getImages() {
        viewModel.getImages(requireActivity())
    }

    /**
     * method to subscribe observer for image response received from view model
     */
    private fun subscribeImageObserver() {
        viewModel.imageResponse.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                Log.i("ImageList", it.toString())
                setupGridView(sortImagesByDate(it))
            } else {
                Utils.showToast(requireActivity(), getString(R.string.error_image_list))
            }
        })
    }

    /**
     * method to sort image list by latest
     */
    private fun sortImagesByDate(list: List<ImageResponseItem>): List<ImageResponseItem> {
        val sortedList = list.sortedByDescending { it.date }
        return sortedList
    }

    /**
     * method to setup gridview adapter
     */
    private fun setupGridView(list: List<ImageResponseItem>) {
        gvAdapter = ImageGridAdapter(requireActivity(), list, ::onImageSelected)
        binding.gvImages.adapter = gvAdapter
    }

    /**
     * method to handle image selection from list
     */
    private fun onImageSelected(list: List<ImageResponseItem>, pos: Int) {
        viewModel.setImageListData(list)
        viewModel.setImagePositionData(pos)
        findNavController().navigate(R.id.action_imageGridFragment_to_fullScreenFragment)
    }

}