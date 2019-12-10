package com.myquotes.feature.main

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.myquotes.feature.detail.DetailQuotesActivity
import com.myquotes.feature.main.adapter.CategoryAdapter
import com.myquotes.model.DataCategory
import com.myquotes.myquotes.R
import com.myquotes.widget.ShowAlertDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {

    private lateinit var toolbar: Toolbar
    private lateinit var txtJudul: TextView
    private lateinit var recyclerView: RecyclerView
    lateinit var mainPresenter: MainPresenter

    lateinit var adapter: CategoryAdapter
    val gson: Gson = Gson()
    var data: String = ""

    var dialogBuilder: AlertDialog.Builder? = null
    var alertDialog: AlertDialog? = null

    lateinit var showAlertDialog: ShowAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        txtJudul = toolbar.findViewById(R.id.txt_judul)
        txtJudul.text = "My Quotes"

        showAlertDialog = ShowAlertDialog(this)
        mainPresenter = MainPresenter(this)
        mainPresenter.onCreate(this)
        mainPresenter.readAppointments()
    }

    override fun initView() {
        recyclerView = findViewById(R.id.list_recycler_view)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
    }

    override fun berhasil(dataCategoryList: MutableList<DataCategory>) {
        adapter = CategoryAdapter(this, dataCategoryList)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : CategoryAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                val dataCategory = adapter.getItem(pos)
                data = gson.toJson(dataCategory)

                val intent = Intent(this@MainActivity, DetailQuotesActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }
        })
    }

    override fun initListener() {

    }

    override fun showDialog() {
        shimmerLayout.startShimmer()
    }

    override fun hideDialog() {
        shimmerLayout.stopShimmer()
        shimmerLayout.visibility = View.GONE
    }

    override fun noInternetConnection() {
        showAlertDialog.noConnectionShowAlertDialog(R.layout.dialog_negative)
    }

    override fun onFailure(message: String) {

    }
}
