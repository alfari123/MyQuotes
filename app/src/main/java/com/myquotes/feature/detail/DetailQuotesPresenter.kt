package com.myquotes.feature.detail

import android.content.Context
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.myquotes.feature.base.BasePresenter
import com.myquotes.model.DataCategory
import com.myquotes.model.DataQuotes
import java.util.*

class DetailQuotesPresenter : BasePresenter {

    private lateinit var detailQuotesView: DetailQuotesView
    private lateinit var context: Context
    private val database = FirebaseDatabase.getInstance()
    private val dataQuotesList = mutableListOf<DataQuotes>()

    constructor(detailQuotesView: DetailQuotesView) {
        this.detailQuotesView = detailQuotesView
    }


    override fun onCreate(context: Context) {
        this.context = context
        detailQuotesView.initView()
        detailQuotesView.initListener()
    }

    override fun getContext(): Context {
        return context
    }

    override fun onResume() {

    }

    fun getDataBanners(quotesData: String) {
        detailQuotesView.showDialog()
        val categoryRef = database.getReference(quotesData)
        categoryRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w("DATABASE_ERROR", "Error while reading appointments", error.toException())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataQuotesList.clear()
                for (snapshot in dataSnapshot.children) {
                    dataQuotesList.add(snapshot.getValue<DataQuotes>(DataQuotes::class.java)!!)
                }

                detailQuotesView.showDataBanner(dataQuotesList)
                detailQuotesView.hideDialog()
            }

        })
    }


}