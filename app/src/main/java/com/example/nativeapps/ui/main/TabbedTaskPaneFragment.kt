package com.example.nativeapps.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nativeapps.R
import com.example.nativeapps.data.viewmodel.PageViewModel
import com.example.nativeapps.data.viewmodel.PageViewModelFactory
import com.example.nativeapps.databinding.FragmentListBinding
import com.example.nativeapps.repository.firebase.StorageRepository
import java.lang.Exception

/**
 * A placeholder fragment containing a simple view.
 */
class TabbedTaskPaneFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this, PageViewModelFactory(StorageRepository())).get(PageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root = binding.root

        // Set up the required class for the viewpage and recyclerview
        val listView: RecyclerView = binding.taskList
        val llm = LinearLayoutManager(listView.context)
        llm.orientation = LinearLayoutManager.VERTICAL
        listView.layoutManager = llm

        // Retrieve the NavHostFragment to be able to easily pass it down to the list items.
        val navHostFragment =
            parentFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        try {
            val tab = arguments?.getInt("section_number")
            if (tab == 1) {
                pageViewModel.getSavedTODOTasks().observe(viewLifecycleOwner, {
                    listView.adapter = TaskAdapter(it.toTypedArray(), navController)
                })
            } else {
                pageViewModel.getSavedDONETasks().observe(viewLifecycleOwner, {
                    listView.adapter = TaskAdapter(it.toTypedArray(), navController)
                })
            }
        } catch (e: Exception) {
            println("no argument passed for tab number")
        }

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): TabbedTaskPaneFragment {
            return TabbedTaskPaneFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
