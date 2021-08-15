package com.example.nativeapps.ui.main

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.nativeapps.R
import com.example.nativeapps.databinding.ActivityListBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
//        val viewPager: ViewPager2 = binding.pager
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = binding.tabs
//        TabLayoutMediator(tabs, viewPager) { tab, position -> if(position == 0) tab.text = "TODO" else tab.text = "DONE" }.attach()
//        val fab: FloatingActionButton = binding.fab
//
//        fab.setOnClickListener { view ->
//            val list = findViewById<AppBarLayout>(R.id.app_bar_layout)
//            val displayMetrics: DisplayMetrics = list.context.getResources().getDisplayMetrics()
//            val dpHeight = list.height / displayMetrics.density
//            val dpWidth = list.width / displayMetrics.density
//            println("${dpHeight} ${dpWidth}")
//        }
    }
}