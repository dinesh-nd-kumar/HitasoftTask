package com.example.hitasofttask.ui

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.hitasofttask.MyViewModel
import com.example.hitasofttask.databinding.FragmentPostBinding
import com.example.hitasofttask.model.RequUser
import com.example.hitasofttask.model.User


class PostFragment : Fragment(), PostAdapter.PostClickListener,CustomDialogFragment.DialogListener {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    private var viewModel: MyViewModel? = null
    private lateinit var postAdapter: PostAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel?.loadData()
        initRecycler()
        viewModel?.getPostLiveData()?.observe(viewLifecycleOwner) {
            setRV(it?: emptyList())
        }

        binding.addPost.setOnClickListener {
            val dialog = CustomDialogFragment(null,"Add"){ t1,t2 ->
                val post = RequUser(t1,t1+"@gmail.com","Pass@123","https://picsum.photos/800")
                viewModel!!.newPost(post)

            }
            dialog.show(fragmentManager!!, "CustomDialog")


        }
    }



    private fun setRV(list:List<User>){
        postAdapter.productList = list
        postAdapter.notifyDataSetChanged()
    }

    private fun initRecycler(){
        binding.rvView.apply {
            postAdapter = PostAdapter(emptyList(),this@PostFragment)
            adapter = postAdapter

        }
    }

    override fun onPostDelete(p: User, pos: Int) {
        viewModel?.deletePost(p,pos)
    }

    override fun onPostEdit(p: User, pos: Int) {
        val dialog = CustomDialogFragment(p,"Update"){ t1,t2 ->
            p.apply {
                name= t1
                role = t2
            }
            viewModel!!.updateUser(p)


        }
        dialog.show(fragmentManager!!, "CustomDialog")
//        dialog.title.setText(p.name)
//        dialog.body.setText(p.password)




    }

    override fun onAddClicked(title: String, body: String) {
        val post = RequUser(title,title+"@gmail.com","Pass@123","https://picsum.photos/800")
        viewModel!!.newPost(post)
    }

    override fun onCancelClicked() {

    }



}