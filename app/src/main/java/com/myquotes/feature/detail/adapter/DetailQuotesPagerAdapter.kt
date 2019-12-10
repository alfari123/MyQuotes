package com.myquotes.feature.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.myquotes.model.DataQuotes
import com.myquotes.myquotes.R

class DetailQuotesPagerAdapter(private val list: List<DataQuotes>) : PagerAdapter() {

    override fun isViewFromObject(v: View, `object`: Any): Boolean {
        return v === `object` as View
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pager,parent,false)

        val txtQuotes =
            view.findViewById<View>(R.id.txt_quotes) as TextView
        val txtAuthor =
            view.findViewById<View>(R.id.txt_author) as TextView

        txtQuotes.text = list[position].quotes
        txtAuthor.text = list[position].author

        parent.addView(view)
        return view
    }


    override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
        parent.removeView(`object` as View)
    }
}