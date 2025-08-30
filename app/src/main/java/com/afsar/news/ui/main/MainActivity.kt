package com.afsar.news.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.afsar.news.R
import com.afsar.news.databinding.ActivityMainBinding
import com.afsar.news.ui.home.HomeFragment
import com.afsar.news.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        replaceFragment(HomeFragment())

        binding.apply {
            navBottom.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.menu_home -> replaceFragment(HomeFragment())
                    R.id.menu_search -> replaceFragment(SearchFragment())
//                    R.id.menu_analysis -> replaceFragment(HomeFragment())
//                    R.id.menu_scan -> replaceFragment(HomeFragment())
                    else -> {
                        replaceFragment(HomeFragment())
                    }
                }
                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fm_main,fragment)
        fragmentTransaction.commit()
    }
}