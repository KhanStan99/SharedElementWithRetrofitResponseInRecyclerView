package com.trentweet.fragmenttransition.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.trentweet.fragmenttransition.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.content,
                AFragment.newInstance()
            )
            .commit()
    }

    fun showFragmentWithTransition(
        newFragment: Fragment,
        tag: String,
        sharedView: View
    ) {
        supportFragmentManager
            .beginTransaction()
            .addSharedElement(sharedView, ViewCompat.getTransitionName(sharedView).toString())
            .replace(R.id.content, newFragment, tag)
            .addToBackStack(tag)
            .commit()
    }


}
