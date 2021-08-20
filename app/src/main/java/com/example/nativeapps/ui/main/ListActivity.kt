package com.example.nativeapps.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.nativeapps.R
import com.example.nativeapps.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.fab.setOnClickListener {
            findNavController(R.id.nav_host_fragment).navigate(ViewPagerContainerFragmentDirections.actionViewPagerContainerFragmentToTaskAddFragment())
        }
    }
}
