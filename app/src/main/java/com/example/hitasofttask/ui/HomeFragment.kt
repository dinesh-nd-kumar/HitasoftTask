package com.example.hitasofttask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hitasofttask.databinding.FragmentHomeBinding
import com.example.hitasofttask.ui.adapters.FragmentPageAdapter
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter  = FragmentPageAdapter(requireActivity().supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter



        TabLayoutMediator(binding.taplayout,binding.viewPager){ tab , pos ->
            when(pos) {
                0 -> {
                    tab.text = "User"
//                    tab.icon = requireContext().getDrawable(R.drawable.ic_launcher_foreground)
                }
                1 -> {
                    tab.text = "Camera"
//                    tab.icon = requireContext().getDrawable(R.drawable.ic_launcher_foreground)
                }
                2 -> {
                    tab.text = "List"
//                    tab.icon = requireContext().getDrawable(R.drawable.ic_launcher_foreground)
                }

//                else -> InrFragment()
            }

        }.attach()

    }


}