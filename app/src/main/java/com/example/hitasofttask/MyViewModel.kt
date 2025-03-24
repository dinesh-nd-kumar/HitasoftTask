package com.example.hitasofttask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hitasofttask.PostRepo.PostRepo
import com.example.hitasofttask.model.RequUser
import com.example.hitasofttask.model.User
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = PostRepo(application.applicationContext)


    fun loadData() {
        viewModelScope.launch{
            repo.loadData()
        }
    }

    fun getPostLiveData() : LiveData<List<User>> {
        return repo.getLiveData()
    }
    fun deletePost(p:User, pos:Int){
        repo.deletePost(p,pos)
    }

    fun newPost(p:RequUser){
        repo.newPost(p)
    }
    fun updateUser(u :User){
        repo.updateUser(u)
    }


}