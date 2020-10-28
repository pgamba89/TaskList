package com.example.taskstodo.taskList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstodo.data.Task
import com.example.taskstodo.databinding.TaskListItemBinding

class WordListAdapter(val clickListener: TaskListItemListener) :
    ListAdapter<Task, WordListAdapter.ViewHolder>(TaskItemsDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    fun getWordAtPosition(position: Int): Task? {
        return getItem(position)
    }

    class ViewHolder private constructor(val binding: TaskListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Task, clickListener: TaskListItemListener) {
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class TaskItemsDiffCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}

class TaskListItemListener(val clickListener: (task: Long) -> Unit) {
    fun onClick(task: Task) = clickListener(task.id)
}