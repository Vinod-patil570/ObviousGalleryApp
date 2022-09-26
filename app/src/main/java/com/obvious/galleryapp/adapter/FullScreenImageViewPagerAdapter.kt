package com.obvious.galleryapp.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.obviousgalleryapp.model.ImageResponseItem
import com.obvious.galleryapp.R
import java.util.*

/**
 * Adapter class for FullScreen Image viewpager
 */
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
        val progressBar: ProgressBar = view?.findViewById<View>(R.id.progressbar) as ProgressBar
        Glide.with(context).load(imageList.get(position).url)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

            }
            ).centerCrop().into(imageView)
        Objects.requireNonNull(container).addView(view)


        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}