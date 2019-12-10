package com.myquotes.feature.base

import android.content.Context

interface BasePresenter {
    fun onCreate(context: Context)
    abstract fun getContext(): Context
    fun onResume()
}