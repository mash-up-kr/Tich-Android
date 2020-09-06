package com.example.tichandroid.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tichandroid.R
import kotlinx.android.synthetic.main.item_walk.view.*

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_walk, parent, false)
        )

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) = holder.itemView.run {
        if (position == 0) {
            txt_walk_main.setText(R.string.walk_01_m)
            txt_walk_sub.setText(R.string.walk_01_s)
        }
        if (position == 1) {
            txt_walk_main.setText(R.string.walk_02_m)
            txt_walk_sub.setText(R.string.walk_02_s)
        }
        if(position == 2) {
            txt_walk_main.setText(R.string.walk_02_m)
            txt_walk_sub.setText(R.string.walk_02_s)
        }
    }

    class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}