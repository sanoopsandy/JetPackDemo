package com.example.jetpackdemo.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.model.local.UsersPost
import com.example.jetpackdemo.base.networking.DataResult
import com.example.jetpackdemo.common.DIHandler
import com.example.jetpackdemo.common.adapters.BaseRecyclerAdapter
import com.example.jetpackdemo.databinding.ActivityMainBinding
import com.example.jetpackdemo.list.di.ListComponent
import com.example.jetpackdemo.list.viewModel.ListViewModel
import javax.inject.Inject

class ListActivity : AppCompatActivity() {

    @Inject
    lateinit var context: Context
    @Inject
    lateinit var adapter: BaseRecyclerAdapter

    private val listComponent: ListComponent by lazy { DIHandler.listComponent() }
    private val viewModel: ListViewModel by lazy { ViewModelProviders.of(this).get(ListViewModel::class.java) }

    val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main) as ActivityMainBinding }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listComponent.inject(this)
        viewModel.getPosts()
        binding.rvPostList.adapter = adapter
        observerData()
    }

    private fun observerData() {
        viewModel.postDataRepository.observe(this, Observer<DataResult<List<UsersPost>>> { result ->
            when (result) {
                is DataResult.Progress -> result.loading
                is DataResult.Success -> {
                    binding.items = result.data
                }
                is DataResult.Failure -> {
                    Toast.makeText(context, "Failure", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}