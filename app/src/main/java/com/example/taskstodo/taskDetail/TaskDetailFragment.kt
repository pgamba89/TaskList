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
import dagger.hilt.android.AndroidEntryPoint

class TaskDetailFragment : Fragment() {

    private lateinit var modelView: TaskDetailModelView
    private lateinit var taskDetailModelViewFactory: TaskDetailModelViewFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTaskDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_task_detail, container, false
        )

        val application = requireNotNull(this.activity).application
        val args = TaskDetailFragmentArgs.fromBundle(requireArguments())

        taskDetailModelViewFactory = TaskDetailModelViewFactory(args.taskId, application)

        modelView =
            ViewModelProvider(this, taskDetailModelViewFactory).get(TaskDetailModelView::class.java)
        binding.viewModel = modelView
        binding.lifecycleOwner = this

        return binding.root
    }
}