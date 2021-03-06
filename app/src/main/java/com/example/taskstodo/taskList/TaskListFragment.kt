package com.example.taskstodo.taskList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstodo.R
import com.example.taskstodo.data.Task
import com.example.taskstodo.databinding.FragmentTaskListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private val viewModel: TaskListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.clear_data){
            viewModel.deleteAll()
            Toast.makeText(activity, R.string.Erase_All, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTaskListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_task_list, container, false
        )

        setFragmentResultListener("requestKey") { key, bundle ->
            bundle.getString("bundleKey")?.let {
                val word = Task(it)
                viewModel.insert(word)
            }
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = WordListAdapter(TaskListItemListener { id ->
            view?.findNavController()?.navigate(TaskListFragmentDirections.actionTaskListFragmentToTaskDetailFragment(id))
        })

        binding.recyclerviewlist.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewlist.adapter = adapter

        viewModel.allTasks.observe(viewLifecycleOwner, Observer { words ->
            words?.let { adapter.submitList(it) }
        })

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val task: Task? = adapter.getWordAtPosition(position)
                task?.let {
                    viewModel.delete(it)
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerviewlist)

        binding.fab.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_taskListFragment_to_taskAddFragment)
        }
        return binding.root
    }
}