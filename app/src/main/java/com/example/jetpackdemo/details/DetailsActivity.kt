package com.example.jetpackdemo.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.jetpackdemo.R
import com.example.jetpackdemo.base.model.local.Comment
import com.example.jetpackdemo.base.model.local.UsersPost
import com.example.jetpackdemo.base.networking.DataResult
import com.example.jetpackdemo.common.DIHandler
import com.example.jetpackdemo.common.adapters.BaseRecyclerAdapter
import com.example.jetpackdemo.databinding.ActivityDetailsBinding
import com.example.jetpackdemo.details.viewModel.DetailsViewModel
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: BaseRecyclerAdapter

    private val viewModel: DetailsViewModel by lazy { ViewModelProviders.of(this).get(DetailsViewModel::class.java) }
    private val binding: ActivityDetailsBinding by lazy {
        DataBindingUtil.setContentView<ActivityDetailsBinding>(this@DetailsActivity, R.layout.activity_details)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DIHandler.detailsComponent().inject(this)
        val bundle = intent.getBundleExtra("details")
        val usersPost = bundle.getParcelable("usersPost") as UsersPost
        viewModel.fetchComments(usersPost.postId)
        with(binding) {
            postUser = usersPost
            items = ArrayList<Comment>()
            rvCommentList.adapter = adapter
        }
        observeCommentData()
    }

    private fun observeCommentData() {
        viewModel.commentDataRepo.observe(this, Observer<DataResult<List<Comment>>> { result ->
            when (result) {
                is DataResult.Progress -> true

                is DataResult.Success -> {
                    binding.items = result.data
                }

                is DataResult.Failure -> {
                    Toast.makeText(this, "Failure", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

}