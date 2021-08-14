package com.example.nativeapps.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nativeapps.data.viewmodel.PageViewModel
import com.example.nativeapps.databinding.FragmentListBinding
import java.lang.Exception

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root = binding.root

        val listView: RecyclerView = binding.taskList
        val llm = LinearLayoutManager(listView.context)
        llm.orientation = LinearLayoutManager.VERTICAL

        listView.layoutManager = llm
        try {
            val tab = arguments?.getInt("section_number")
            if(tab == 1){
                pageViewModel.todoList.observe(viewLifecycleOwner, {
                    listView.adapter = TaskAdapter(it.toTypedArray());
                })
            }else{
                pageViewModel.doneList.observe(viewLifecycleOwner, {
                    listView.adapter = TaskAdapter(it.toTypedArray());
                })
            }
        }catch (e: Exception) {
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
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
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