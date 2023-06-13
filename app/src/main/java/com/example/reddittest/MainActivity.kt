package com.example.reddittest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.reddittest.fragments.CommentsListFragment
import com.example.reddittest.fragments.PostsListFragment
import com.example.reddittest.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main)  {

    val mainViewModel : MainViewModel by viewModels()
    lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar = supportActionBar!!
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setIcon(R.drawable.ic_launcher_foreground)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<PostsListFragment>(R.id.frame)
            }
        }
    }

    fun showComments(permalink: String) {
        Log.d("linnnnnnnn", permalink)
        if (!permalink.trim().equals("")) {
            mainViewModel.setcommentsLink(permalink)
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.frame, CommentsListFragment())
                addToBackStack(null)
            }
        }else{
            Toast.makeText(this,getString(R.string.no_comments_found),Toast.LENGTH_LONG).show()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) supportFragmentManager.popBackStack() else onBackPressedDispatcher.onBackPressed()
    }

}