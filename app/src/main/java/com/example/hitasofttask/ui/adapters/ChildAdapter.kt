package com.example.hitasofttask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hitasofttask.databinding.ItemChildBinding
import com.example.hitasofttask.model.ChildItem

class ChildAdapter(
    private val childList: MutableList<ChildItem>,

) : RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    inner class ChildViewHolder(val binding: ItemChildBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val binding = ItemChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val child = childList[position]
        holder.binding.apply {
            tvChildName.text = child.name
            cbChild.isChecked = child.isChecked

            cbChild.setOnCheckedChangeListener { _, isChecked ->
                child.isChecked = isChecked
            }
        }
    }

    override fun getItemCount() = childList.size
}
