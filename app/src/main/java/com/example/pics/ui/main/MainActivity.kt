package com.example.pics.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pics.R
import com.example.pics.ui.main.pics.PicsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PicsFragment.newInstance())
                .commitNow()
        }
    }
}
