package com.example.nativeapps.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.nativeapps.R
import com.example.nativeapps.data.viewmodel.TaskDetailViewModel
import com.example.nativeapps.databinding.TaskDetailFragmentBinding

class TaskDetailFragment : Fragment() {

    // declare the view bindings
    private var _binding: TaskDetailFragmentBinding? = null
    private val binding get() = _binding!!
    // declare the nav args
    private val args : TaskDetailFragmentArgs by navArgs()
    // declare the viewModel
    private lateinit var taskDetailViewModel: TaskDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // _binding = TaskDetailFragmentBinding.inflate(inflater, container, false)
        _binding = DataBindingUtil.inflate(inflater, R.layout.task_detail_fragment, container, false)
        taskDetailViewModel = ViewModelProvider(this).get(TaskDetailViewModel::class.java)
        binding.viewModel = taskDetailViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        val view = binding.root


        // Use view binding the fill the detail screen as it is not complex enough to warrant using a databinding
        args.taskId?.let { taskDetailViewModel.setTaskById(it) }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}