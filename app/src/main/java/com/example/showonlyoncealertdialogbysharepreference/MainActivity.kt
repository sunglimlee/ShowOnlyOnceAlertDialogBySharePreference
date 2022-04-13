package com.example.showonlyoncealertdialogbysharepreference

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sharedPreferences = getSharedPreferences("ShowOnlyOnceDialog", Context.MODE_PRIVATE)
        val isFirstTime = sharedPreferences.getBoolean("isFirstTime", true)
        if (isFirstTime) {
            showDialog()
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle("One time dialog")
            .setMessage("This should only be shown once")
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            }).create().show()
        val sharedPreferences = this.getSharedPreferences("ShowOnlyOnceDialog", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("isFirstTime", false)
        editor.apply()
    }
}