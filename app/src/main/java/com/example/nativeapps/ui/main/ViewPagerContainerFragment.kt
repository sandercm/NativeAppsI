package com.example.nativeapps.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.nativeapps.databinding.FragmentViewpagerContainerBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerContainerFragment : Fragment() {
    private var _binding: FragmentViewpagerContainerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewpagerContainerBinding.inflate(inflater, container, false)
        val root = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = binding.viewPager
        val sectionsPagerAdapter = SectionsPagerAdapter(this.requireActivity())
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabLayout

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            println(tab)
            println(position)
            when (position) {
                0 -> tab.text = "TODO"
                1 -> tab.text = "DONE"
            }
        }.attach()
    }
}