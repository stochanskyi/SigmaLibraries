package com.stochanskyi.librariesdemo.presentaiton.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.stochanskyi.librariesdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).let {
            setContentView(it.root)
        }
    }
}