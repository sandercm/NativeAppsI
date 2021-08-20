package com.example.nativeapps.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nativeapps.data.model.Task
import com.example.nativeapps.databinding.TextRowItemBinding

class TaskAdapter(private val dataSet: Array<Task>, private val navHostFragment: NavController) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: TextRowItemBinding, navHostFragment: NavController) : RecyclerView.ViewHolder(view.root) {
        val binding = view

        fun bind(taskData: Task) {
            binding.taskData = taskData
        }

        private fun getId(): String? {
            return binding.taskData?.name
        }

        init {
            // Define click listener for the ViewHolder's View.
            // This will navigate away from the tabbed list view and show a detailed view of the TASK.
            view.root.setOnClickListener {
                val action = ViewPagerContainerFragmentDirections.actionViewPagerContainerFragmentToTaskDetailFragment(getId())
                navHostFragment.navigate(action)
            }
        }

        companion object {
            fun from(parent: ViewGroup, navHostFragment: NavController): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TextRowItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding, navHostFragment)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        return ViewHolder.from(viewGroup, navHostFragment)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val task = dataSet[position]
        viewHolder.bind(task)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}
