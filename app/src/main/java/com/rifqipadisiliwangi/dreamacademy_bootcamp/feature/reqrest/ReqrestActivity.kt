package com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.reqrest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model.User
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.api.ReqresApi
import com.rifqipadisiliwangi.dreamacademy_bootcamp.databinding.ActivityReqrestBinding

class ReqrestActivity : AppCompatActivity(), ReqrestContract.View {

    private lateinit var binding : ActivityReqrestBinding
    private val adapter: UserAdapter by lazy { UserAdapter() }
    private val recipeLiveData = MutableLiveData<List<User>>(listOf())
    private lateinit var presenter: ReqrestPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReqrestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = ReqrestPresenter(this@ReqrestActivity, ReqresApi()).apply {
            onAttach()
        }

        binding.rvRecipes.apply {
            adapter = this@ReqrestActivity.adapter
            layoutManager = LinearLayoutManager(this@ReqrestActivity)
        }

        Log.e("Data Ditemukan:", adapter.toString())

        recipeLiveData.observe(this){
            adapter.submitList(it)
        }
    }

    override fun onLoading() {
        if (!isFinishing){
            binding.loader.visibility = View.VISIBLE
            binding.rvRecipes.visibility = View.GONE
        }
    }

    override fun onFinishedLoading() {
        if (!isFinishing){
            binding.loader.visibility = View.GONE
            binding.rvRecipes.visibility = View.VISIBLE
        }
    }

    override fun onError(message: String) {
    }

    override fun onSuccessGetUser(user: List<User>) {
        adapter.submitList(user)
    }

    private fun showProgress(isShown: Boolean){
    }
}