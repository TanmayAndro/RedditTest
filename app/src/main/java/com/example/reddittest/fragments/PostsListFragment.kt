package com.example.reddittest.fragments

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reddittest.MainActivity
import com.example.reddittest.adapters.MainAdapter
import com.example.reddittest.R
import com.example.reddittest.dc.Children
import com.example.reddittest.utils.RecyclerviewOnClickListener
import com.example.reddittest.utils.Status
import com.example.reddittest.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsListFragment : Fragment(R.layout.post_list_layout), RecyclerviewOnClickListener {

    private lateinit var rvPosts: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var ibSearch: ImageButton
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    private var mainActivity: MainActivity? = null
    private lateinit var mcontext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
        mcontext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPosts = view.findViewById(R.id.rvPosts)
        etSearch = view.findViewById(R.id.etSearch)
        ibSearch = view.findViewById(R.id.ibSearch)

        setupUI()
        setupObserver()
        mainViewModel.fetchPosts(getString(R.string.usa))

        ibSearch.setOnClickListener(View.OnClickListener {
            if (!etSearch.text.equals("")) {
                mainViewModel.fetchPosts(etSearch.text.toString())
                etSearch.setText("")
            } else {
                Toast.makeText(
                    mainActivity,
                    getString(R.string.valid_input_msg),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun setupUI() {
        rvPosts.layoutManager = LinearLayoutManager(mcontext)
        adapter = MainAdapter(this, arrayListOf())
        rvPosts.addItemDecoration(
            DividerItemDecoration(
                rvPosts.context,
                (rvPosts.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvPosts.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.posts.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { renderList(it.data.children) }
                }
                Status.LOADING -> {
                    //May show progress bar here
                }
                Status.ERROR -> {
                    val toast: Toast = Toast.makeText(
                        mainActivity,
                        getString(R.string.nothing_found),
                        Toast.LENGTH_LONG
                    )
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
            }
        })
    }

    private fun renderList(feeds: List<Children>) {
        adapter.addData(feeds)
    }

    override fun recyclerviewClick(children: Children) {
        mainActivity?.showComments(children.data.permalink)
    }

}