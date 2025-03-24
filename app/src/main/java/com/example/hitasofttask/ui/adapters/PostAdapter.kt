package com.example.hitasofttask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hitasofttask.databinding.PostRowItemBinding
import com.example.hitasofttask.model.User

class PostAdapter(var productList: List<User>, val listner: PostClickListener)
    : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            PostRowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false))
    }

    override fun getItemCount()= productList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val name = productList[position]
        holder.bindData(name)
    }
    inner class PostViewHolder(val binding:PostRowItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(p: User?){
            binding.apply {
                user = p
//                ratingTextview.text = "${p?.stock} "
                root.setOnClickListener{
                    listner.onPostEdit(p!!,adapterPosition)
                }
                imageButton.setOnClickListener {
                    listner.onPostDelete(p!!,adapterPosition)
                }
            }
        }

    }

     interface PostClickListener{
         fun onPostDelete(p: User, pos:Int)
        fun onPostEdit(p: User, pos:Int)
    }

}