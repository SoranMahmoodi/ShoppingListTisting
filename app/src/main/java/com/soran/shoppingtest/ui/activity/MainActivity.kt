package com.soran.shoppingtest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soran.shoppingtest.R
import com.soran.shoppingtest.ui.base.FragmentFactoryShopping
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactoryShopping: FragmentFactoryShopping

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactoryShopping
        setContentView(R.layout.activity_main)
    }
}
