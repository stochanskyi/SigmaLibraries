package com.stochanskyi.librariesdemo.presentaiton.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.stochanskyi.librariesdemo.R
import com.stochanskyi.librariesdemo.databinding.ActivityMainBinding
import com.stochanskyi.librariesdemo.presentaiton.imageloading.ImageLoadersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            return
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, ImageLoadersFragment.newInstance())
            .commit()
    }

}