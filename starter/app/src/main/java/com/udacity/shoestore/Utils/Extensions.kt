package com.udacity.shoestore.Utils

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.MainActivity

fun Fragment.setBaseActivityFragmentsToolbar(title: String, toolbar: Toolbar, textView: TextView) {
    (activity as MainActivity).apply {
        setHasOptionsMenu(true)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        toolbar.setupWithNavController(navController, appBarConfiguration)
        textView.text = title
    }
}
