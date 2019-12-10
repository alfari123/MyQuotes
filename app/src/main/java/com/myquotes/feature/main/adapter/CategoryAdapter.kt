package com.myquotes.feature.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myquotes.model.DataCategory
import com.myquotes.myquotes.R
import androidx.annotation.Nullable


class CategoryAdapter(
    private val context: Context,
    private val dataCategoryList: List<DataCategory>
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(dataCategoryList[position])
    }

    override fun getItemCount(): Int {
        return dataCategoryList.size
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        fun bindItems(dataCategory: DataCategory) {
            val txtName = itemView.findViewById(R.id.textViewUsername) as TextView
            val imgBg = itemView.findViewById(R.id.img_quotes) as ImageView

            txtName.text = dataCategory.category
            Glide.with(context)
                .load(dataCategory.urlToImage)
                .centerCrop()
                .into(imgBg)
        }

        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    @Nullable
    fun getItem(position: Int): DataCategory {
        return dataCategoryList.get(position)
    }
}