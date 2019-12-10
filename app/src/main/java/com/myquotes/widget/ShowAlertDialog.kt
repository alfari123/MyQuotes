package com.myquotes.widget

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import android.view.LayoutInflater
import android.widget.Button
import com.myquotes.myquotes.R

class ShowAlertDialog(private var context: Context) {

    var dialogBuilder: AlertDialog.Builder? = null
    var alertDialog: AlertDialog? = null

    fun noConnectionShowAlertDialog(layout: Int) {
        dialogBuilder = AlertDialog.Builder(context)
        val layoutView = LayoutInflater.from(context).inflate(layout, null)
        val dialogButton: Button = layoutView.findViewById(R.id.btnDialog)
        dialogBuilder!!.setView(layoutView)
        alertDialog = dialogBuilder!!.create()
        alertDialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        alertDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog?.show()
        dialogButton.setOnClickListener {
            //            alertDialog?.dismiss()
            context.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS));
        }
    }
}