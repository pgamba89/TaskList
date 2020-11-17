package com.example.taskstodo.taskAdd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.taskstodo.R
import com.example.taskstodo.databinding.FragmentTaskAddBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskAddFragment : Fragment() {

    private val modelView: TaskAddModelView by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTaskAddBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_task_add, container, false
        )

        binding.viewModel = modelView
        binding.lifecycleOwner = this

        binding.buttonSave.setOnClickListener {
            if (binding.editWord.text.isEmpty()) {
                Toast.makeText(activity, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
            } else {
                val task = binding.editWord.text.toString()
                modelView.saveTask(task)
            }
        }

        modelView.navigateToTaskList.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                setFragmentResult("requestKey", bundleOf("bundleKey" to it))
                this.findNavController()
                    .navigate(TaskAddFragmentDirections.actionTaskAddFragmentToTaskListFragment())
            }
        })

        return binding.root
    }
}