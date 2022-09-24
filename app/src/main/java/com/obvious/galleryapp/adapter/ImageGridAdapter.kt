package com.example.obviousgalleryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.obviousgalleryapp.model.ImageResponseItem
import com.obvious.galleryapp.R
import com.obvious.galleryapp.Utils
import kotlin.reflect.KFunction2

class ImageGridAdapter(
    private val context: Context,
    private val imageList: List<ImageResponseItem>,
    val onImageSelected: KFunction2<List<ImageResponseItem>, Int, Unit>,
) : BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private lateinit var imageView: ImageView
    private lateinit var parentView: FrameLayout
    private lateinit var tvDate: TextView

    override fun getCount(): Int {
        return imageList.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        }
        if (convertView == null) {
            convertView = layoutInflater?.inflate(R.layout.image_gv_item, null)
        }

        imageView = convertView!!.findViewById(R.id.imageView)
        parentView = convertView.findViewById(R.id.parentView)
        tvDate = convertView.findViewById(R.id.tvDate)
        tvDate.text = Utils.getParsedDate(imageList.get(position).date!!)
        Glide.with(context).load(imageList.get(position).url)
            .placeholder(Utils.getGlidePlaceHolder(context)).centerCrop().into(imageView)
        parentView.setOnClickListener(View.OnClickListener {
            onImageSelected(imageList, position)
        })

        return convertView
    }
}