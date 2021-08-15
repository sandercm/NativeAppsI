package com.example.nativeapps.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.nativeapps.data.model.Task
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskDetailViewModel = ViewModelProvider(this).get(TaskDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TaskDetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        // Use view binding the fill the detail screen as it is not complex enough to warrant using a databinding
        val task: Task? = args.taskId?.let { taskDetailViewModel.getTaskById(it) };
        if (task != null) {
            binding.taskTitle.text = task.name
            binding.taskBody.text = task.description
            binding.completedSwitch.isChecked = task.completed
            binding.completedSwitch.setOnCheckedChangeListener { _, b ->
                taskDetailViewModel.setCompletedStatus(task.name, b)
            }
        }

        return view
    }

}