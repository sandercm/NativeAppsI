package com.example.nativeapps.ui.taskadd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nativeapps.data.model.Task
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
        println("calling on create")
        taskAddViewModel = ViewModelProvider(this).get(TaskAddViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTaskAddBinding.inflate(inflater, container, false)
        val root = binding.root

        binding.addTask.setOnClickListener {
            taskAddViewModel.storeTask(Task(binding.taskName.text.toString(), binding.taskDescription.text.toString(), false))
            val action = TaskAddFragmentDirections.actionTaskAddFragmentToViewPagerContainerFragment()
            findNavController().navigate(action)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}