package com.myquotes.feature.main

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.myquotes.feature.base.BasePresenter
import com.myquotes.feature.main.adapter.CategoryAdapter
import com.myquotes.helper.isConnectedToNetwork
import com.myquotes.model.DataCategory
import com.myquotes.widget.toast

class MainPresenter : BasePresenter {


    lateinit var mainView: MainView
    private val database = FirebaseDatabase.getInstance()
    private val categoryRef = database.getReference("category_quotes")
    private val dataCategorylist = mutableListOf<DataCategory>()
    private lateinit var context: Context

    constructor(mainView: MainView) {
        this.mainView = mainView
    }

    override fun getContext(): Context {
        return context
    }

    override fun onCreate(context: Context) {
        this.context = context
        mainView.initView()
        mainView.initListener()
    }

    fun readAppointments() {
        mainView.showDialog()
        if (context.isConnectedToNetwork()) {
//            context.toast("KONEK")
        } else {
            mainView.noInternetConnection()
        }
        categoryRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w("DATABASE_ERROR", "Error while reading appointments", error.toException())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataCategorylist.clear()
                for (snapshot in dataSnapshot.children) {
                    dataCategorylist.add(snapshot.getValue<DataCategory>(DataCategory::class.java)!!)
                }

                mainView.berhasil(dataCategorylist)
                mainView.hideDialog()
            }

        })
    }

    override fun onResume() {

    }
}