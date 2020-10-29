package com.example.taskstodo.taskAdd

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.taskstodo.R
import com.example.taskstodo.databinding.FragmentTaskAddBinding

class TaskAddFragment : Fragment() {
    private lateinit var taskAddViewModel: TaskAddModelView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTaskAddBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_task_add, container, false
        )

        taskAddViewModel = ViewModelProvider(this).get(TaskAddModelView::class.java)
        binding.viewModel = taskAddViewModel
        binding.lifecycleOwner = this

        binding.buttonSave.setOnClickListener { view: View ->
            if (TextUtils.isEmpty(binding.editWord.text)) {
                Toast.makeText(activity, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
            } else {
                val task = binding.editWord.text.toString()
                setFragmentResult("requestKey", bundleOf("bundleKey" to task))
                view.findNavController()
                    .navigate(TaskAddFragmentDirections.actionTaskAddFragmentToTaskListFragment())
            }
        }

        return binding.root
    }
}