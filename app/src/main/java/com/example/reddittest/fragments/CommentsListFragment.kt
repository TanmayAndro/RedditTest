package com.example.reddittest.fragments

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reddittest.adapters.CommentListAdapter
import com.example.reddittest.MainActivity
import com.example.reddittest.R
import com.example.reddittest.dc.ChildrenX
import com.example.reddittest.dc.CommentsItem
import com.example.reddittest.utils.Status
import com.example.reddittest.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsListFragment : Fragment(R.layout.comments_layout){

    lateinit var mainViewModel : MainViewModel
    private lateinit var adapter: CommentListAdapter
    private var mainActivity : MainActivity? = null
    private lateinit var mcontext: Context
    private lateinit var rvComments : RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
        this.mainViewModel = mainActivity!!.mainViewModel
        mcontext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvComments = view.findViewById<RecyclerView>(R.id.rvComments)

        setupUI()
        setupCommentsObserver()
        mainViewModel.commentsLink.let { mainViewModel.fetchComments(it) }

    }

    private fun setupUI() {
        rvComments.layoutManager = LinearLayoutManager(mcontext)
        adapter = CommentListAdapter(arrayListOf())
        rvComments.addItemDecoration(
            DividerItemDecoration(
                rvComments.context,
                (rvComments.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvComments.adapter = adapter
    }

    private fun setupCommentsObserver() {
        mainViewModel.comments?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { renderList(it) }
                }
                Status.LOADING -> {
                    val toast : Toast =  Toast.makeText(mainActivity, getString(R.string.loading), Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
                Status.ERROR -> {
                    val toast : Toast =  Toast.makeText(mainActivity, getString(R.string.some_error), Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
            }
        })
    }

    private fun renderList(comments: List<CommentsItem>) {
        val list: MutableList<ChildrenX> = mutableListOf()
        for (i in comments){
            list.addAll(i.data.children)
        }
        adapter.addData(list)
    }

}
