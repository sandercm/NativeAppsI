package com.example.nativeapps.ui.taskadd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.nativeapps.R
import com.example.nativeapps.data.model.Task
import com.example.nativeapps.data.viewmodel.TaskAddFragmentViewModelFactory
import com.example.nativeapps.data.viewmodel.TaskAddViewModel
import com.example.nativeapps.databinding.FragmentTaskAddBinding

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class TaskAddFragment : Fragment() {

    private var _binding: FragmentTaskAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAddViewModel: TaskAddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // DataBindingUtil.inflate(inflater, R.layout.task_detail_fragment, container, false)
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_add, container, false)
        taskAddViewModel = ViewModelProvider(this, TaskAddFragmentViewModelFactory(requireActivity().application)).get(TaskAddViewModel::class.java)

        binding.viewModel = taskAddViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        taskAddViewModel.load(binding.imageView)

        binding.addTask.setOnClickListener {
            taskAddViewModel.storeTask(Task(binding.taskName.text.toString(), binding.taskDescription.text.toString(), false))
            val action = TaskAddFragmentDirections.actionTaskAddFragmentToViewPagerContainerFragment()
            findNavController().navigate(action)
        }
        val root = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
