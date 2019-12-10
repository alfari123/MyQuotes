package com.myquotes.feature.base

interface BaseView {
    fun initView()
    fun initListener()
    fun showDialog()
    fun hideDialog()
    fun noInternetConnection()
    fun onFailure(message: String)
}