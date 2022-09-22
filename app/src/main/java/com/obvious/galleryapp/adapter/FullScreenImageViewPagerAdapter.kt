package com.obvious.galleryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.obviousgalleryapp.model.ImageResponseItem
import com.obvious.galleryapp.R
import com.obvious.galleryapp.Utils
import java.util.*

class FullScreenImageViewPagerAdapter(
    private val context: Context,
    private val imageList: List<ImageResponseItem>,
    private val selectedPosition: Int
) : PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        val view = layoutInflater?.inflate(R.layout.image_vp_item, container, false)
        val imageView: ImageView = view?.findViewById<View>(R.id.vpImageView) as ImageView
        Glide.with(context).load(imageList.get(position).url).centerCrop().into(imageView)
        Objects.requireNonNull(container).addView(view)


        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}