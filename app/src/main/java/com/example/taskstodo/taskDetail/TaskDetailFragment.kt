package com.example.taskstodo.taskDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.taskstodo.R
import com.example.taskstodo.databinding.FragmentTaskDetailBinding

class TaskDetailFragment : Fragment() {

    private lateinit var taskDetailModelView: TaskDetailModelView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTaskDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_task_detail, container, false
        )

        taskDetailModelView = ViewModelProvider(this).get(TaskDetailModelView::class.java)
        binding.viewModel = taskDetailModelView
        binding.lifecycleOwner = this

        return binding.root
    }
}