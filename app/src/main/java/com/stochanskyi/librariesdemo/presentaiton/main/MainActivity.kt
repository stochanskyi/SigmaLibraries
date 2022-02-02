package com.stochanskyi.librariesdemo.presentaiton.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.databinding.ActivityMainBinding
import com.stochanskyi.librariesdemo.presentaiton.simplecall.SimpleCallFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            return
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, SimpleCallFragment.newInstance())
            .commit()
    }


    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return super.onRetainCustomNonConfigurationInstance()
    }
}