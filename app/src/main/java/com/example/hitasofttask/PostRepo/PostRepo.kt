package com.example.hitasofttask.PostRepo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hitasofttask.PostRepo.roomdb.DatabaseBuilder
import com.example.hitasofttask.model.RequUser
import com.example.hitasofttask.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepo(val context: Context) {
    private var postLiveData = MutableLiveData<List<User>>()
    val data : LiveData<List<User>> get() = postLiveData

    suspend fun loadData() {
        fetchPostsFromRoom()
    }
    fun getLiveData(): LiveData<List<User>> {
        return data
    }
    fun newPost(p: RequUser){
        CoroutineScope(Dispatchers.IO).launch {
            val postRes = Retrofit.api.newPost(p)
            if (postRes.isSuccessful){

                Log.d("NewUser", "onResponse:${postRes.body()} ")
                DatabaseBuilder.getInstance(context).postDao().insertPost(postRes.body()!!)
                fetchPostsFromRoom()

            }
            else{
                withContext(Dispatchers.Main){
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }



        }
    }

    fun deletePost(post: User, pos:Int) {

        CoroutineScope(Dispatchers.IO).launch {
            deleteInRoom(post)
            try {
                val response = Retrofit.api.deletePost(post.id)
                if (response.isSuccessful) {


                    /*val updatedList = DatabaseBuilder.getInstance(context).postDao().getAllPosts()

                    withContext(Dispatchers.Main) {
                        postLiveData.value = updatedList
                    }*/
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }


    private  fun fetchProducts() {
        val call = Retrofit.api.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                if(response.isSuccessful){
                    saveInRoom(response.body())
                    postLiveData.postValue(response.body())

                    response.body()?.forEach{
                        Log.d("Users", "onResponse:${it.toString()} ")
                    }
                }
                else{

                }

            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }
        })
    }

    private fun saveInRoom(body: List<User>?) {
        GlobalScope.launch {
            DatabaseBuilder.getInstance(context).postDao().insertAllPosts(body?: emptyList())
        }


    }

    private suspend fun fetchPostsFromRoom() {
        CoroutineScope(Dispatchers.IO).launch{
        DatabaseBuilder.run {
            val data = getInstance(context).postDao().getAllPosts()
            if (data.isEmpty())
                fetchProducts()
            postLiveData.postValue(data)
        }
        }

    }
    private fun deleteInRoom(post: User){
        CoroutineScope(Dispatchers.IO).launch {
            DatabaseBuilder.getInstance(context).postDao().deletePost(post)
            fetchPostsFromRoom()
        }
    }

    fun updateUser(u: User) {

        CoroutineScope(Dispatchers.IO).launch {
            val postRes = Retrofit.api.updatePost(u,u.id)
            if (postRes.isSuccessful){

                Log.d("UserUpdate", "onResponse:${postRes.body()} ")
                DatabaseBuilder.getInstance(context).postDao().updatePost(postRes.body()!!)
                fetchPostsFromRoom()
            }
        }
    }

}