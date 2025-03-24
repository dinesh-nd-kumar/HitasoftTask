package com.example.hitasofttask.ui.adapters

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hitasofttask.databinding.ItemParentBinding
import com.example.hitasofttask.model.ParentItem

class ParentAdapter(
    private val parentList: MutableList<ParentItem>
) : RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    inner class ParentViewHolder(val binding: ItemParentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val binding = ItemParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentViewHolder(binding)
    }

    override fun getItemId(position: Int) = parentList[position].id.toLong()
    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val parentItem = parentList[position]
        val childAdapter = ChildAdapter(parentItem.childList)

        holder.binding.apply {
            tvParentName.text = parentItem.name
            cbParent.isChecked = parentItem.isChecked
            rvChildItems.layoutManager = LinearLayoutManager(root.context)
            rvChildItems.adapter = childAdapter
            rvChildItems.visibility = if (parentItem.isExpanded) View.VISIBLE else View.GONE

            btnExpand.setOnClickListener {
                parentItem.isExpanded = !parentItem.isExpanded
                notifyItemChanged(position)
            }

            cbParent.setOnCheckedChangeListener(null)
            cbParent.isChecked = parentItem.isChecked

            cbParent.setOnCheckedChangeListener { _, isChecked ->
                parentItem.isChecked = isChecked
                parentItem.childList.forEach { it.isChecked = isChecked }
                Handler(Looper.getMainLooper()).post {
                    notifyItemChanged(position) // Refresh parent
                }
            }
        }
    }

    override fun getItemCount() = parentList.size
}
