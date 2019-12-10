package com.myquotes.feature.detail

import com.myquotes.feature.base.BaseView
import com.myquotes.model.DataQuotes

interface DetailQuotesView : BaseView {
fun showDataBanner(dataQuotesList: MutableList<DataQuotes>)
}