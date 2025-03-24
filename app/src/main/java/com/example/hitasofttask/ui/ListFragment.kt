package com.example.hitasofttask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hitasofttask.PostRepo.values
import com.example.hitasofttask.R
import com.example.hitasofttask.databinding.FragmentCameraBinding
import com.example.hitasofttask.databinding.FragmentListBinding
import com.example.hitasofttask.model.ChildItem
import com.example.hitasofttask.model.ParentItem
import com.example.hitasofttask.ui.adapters.ParentAdapter


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var parentAdapter: ParentAdapter
    private var parentList = mutableListOf<ParentItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentList = values.list

        parentAdapter = ParentAdapter(parentList)
        binding.rvParent.adapter = parentAdapter
    }


}