package com.myquotes.feature.main

import com.myquotes.feature.base.BaseView
import com.myquotes.model.DataCategory

interface MainView : BaseView{
    fun berhasil(dataCategoryList: MutableList<DataCategory>)
}