package com.myquotes.feature.detail

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.gson.Gson
import com.myquotes.feature.detail.adapter.DetailQuotesPagerAdapter
import com.myquotes.model.DataCategory
import com.myquotes.model.DataQuotes
import com.myquotes.myquotes.R
import com.myquotes.helper.ProgressDialogHelper

class DetailQuotesActivity : AppCompatActivity(), DetailQuotesView {

    private lateinit var detailQuotesPresenter: DetailQuotesPresenter
    var data: String = ""
    var dataJudul: String = ""

    private lateinit var txtJudul: TextView
    private lateinit var toolbar: Toolbar

    private var mViewPager: ViewPager? = null
    lateinit var dataCategory: DataCategory
    private var detailQuotesPagerAdapter: DetailQuotesPagerAdapter? = null
    val gson: Gson = Gson()
    private lateinit var progressDialogHelper: ProgressDialogHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_quotes)

        data = intent.getStringExtra("data")

        dataCategory = gson.fromJson(data, DataCategory::class.java)
        dataJudul = dataCategory.category!!

        toolbar = findViewById(R.id.toolbar)
        txtJudul = toolbar.findViewById(R.id.txt_judul)
        txtJudul.text = dataJudul

        progressDialogHelper = ProgressDialogHelper(this)
        detailQuotesPresenter = DetailQuotesPresenter(this)
        detailQuotesPresenter.onCreate(this)
        detailQuotesPresenter.getDataBanners(dataCategory.type_category!!)
    }


    override fun initView() {
        mViewPager = findViewById(R.id.view_pager)
    }

    override fun initListener() {

    }

    override fun showDialog() {
        progressDialogHelper.show()
    }

    override fun hideDialog() {
        progressDialogHelper.dismiss()
    }

    override fun noInternetConnection() {

    }

    override fun onFailure(message: String) {

    }

    override fun showDataBanner(dataQuotesList: MutableList<DataQuotes>) {
        detailQuotesPagerAdapter = DetailQuotesPagerAdapter(dataQuotesList)
        mViewPager?.adapter = detailQuotesPagerAdapter

//        mCardAdapter?.addCardItem(dataQuotesList)
//        mCardShadowTransformer = ShadowTransform(mViewPager, mCardAdapter)
//        mViewPager?.adapter = mCardAdapter
//        mViewPager?.setPageTransformer(false, mCardShadowTransformer)
//        mViewPager?.offscreenPageLimit = 3
    }
}