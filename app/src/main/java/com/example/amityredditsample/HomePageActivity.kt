package com.example.amityredditsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amity.socialcloud.uikit.community.home.fragments.AmityCommunityHomePageFragment
import com.example.amityredditsample.databinding.AmityActivityHomepageBinding


class HomePageActivity : AppCompatActivity() {

    private lateinit var binding: AmityActivityHomepageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AmityActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment()
    }

    private fun loadFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = AmityCommunityHomePageFragment.newInstance().build()
        fragmentTransaction.replace(
            R.id.fragmentContainer,
            fragment
        )
        fragmentTransaction.commit()
    }
}
