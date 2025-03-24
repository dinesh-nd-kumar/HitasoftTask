package com.example.hitasofttask.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hitasofttask.ui.CameraFragment
import com.example.hitasofttask.ui.ListFragment
import com.example.hitasofttask.ui.UserFragment


class FragmentPageAdapter(
    supportFragmentManager: FragmentManager,
    lifecycle: Lifecycle)
    : FragmentStateAdapter(supportFragmentManager,lifecycle) {


    override fun getItemCount(): Int {

        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 ->  UserFragment()
            1 -> CameraFragment()
            2 -> ListFragment()
            else -> UserFragment()
        }
    }
}