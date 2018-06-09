package com.example.jetpackdemo.common

import android.databinding.BindingAdapter
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.example.jetpackdemo.R
import com.example.jetpackdemo.common.adapters.BaseRecyclerAdapter
import com.squareup.picasso.Picasso


class BindingUtil {

    companion object {

        @JvmStatic
        @BindingAdapter(value = arrayOf("itemLayout", "items"))
        fun configureAdapter(recyclerView: RecyclerView, @LayoutRes layoutId: Int, items: List<*>) {
            try {
                val mLayoutManager = LinearLayoutManager(recyclerView.context)
                recyclerView.layoutManager = mLayoutManager
                val adapter = recyclerView.adapter as BaseRecyclerAdapter
                adapter.setLayoutId(layoutId)
                adapter.setItems(items)
                adapter.setContext(recyclerView.context)
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, imageUrl: String) {
            if (imageUrl.isNotEmpty()) {
                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder_image)
                        .into(view)
            }
        }
    }
}