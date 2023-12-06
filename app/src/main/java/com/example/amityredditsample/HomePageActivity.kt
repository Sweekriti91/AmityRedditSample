package com.example.amityredditsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.amitywrap.AmityWrap


class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.amity_activity_homepage))

        loadFragment()
    }

    private fun loadFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val test = AmityWrap()
        val fragment = test.getHomeNewsFeed()
        fragmentTransaction.replace(
            R.id.content,
            fragment,
            fragment.javaClass.simpleName
        )
        fragmentTransaction.commit()
    }
}
