package com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model.MakeUpItem
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.api.MakeupApi
import com.rifqipadisiliwangi.dreamacademy_bootcamp.databinding.ActivityMainBinding
import com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.adapters.MakeUpAdapter

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding : ActivityMainBinding
    private val adapter: MakeUpAdapter by lazy { MakeUpAdapter() }
    private val recipeLiveData = MutableLiveData<List<MakeUpItem>>(listOf())
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this, MakeupApi()).apply {
            onAttach()
        }

        binding.rvRecipes.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }


        recipeLiveData.observe(this) {
            adapter.submitList(it)
        }

        binding.btnAsyncCall.setOnClickListener {
            presenter.getMakeUp()
        }

    }

    override fun onLoading() {
        showProgress(true)
    }

    override fun onFinishedLoading() {
        showProgress(false)
    }

    override fun onError(message: String) {
        val dialog = AlertDialog.Builder(this)
        dialog
            .setMessage(message)
            .setPositiveButton("Ok") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()
            .show()
    }


    private fun showProgress(isShown: Boolean) {
        binding.progressIndicator.isVisible = isShown
    }
}