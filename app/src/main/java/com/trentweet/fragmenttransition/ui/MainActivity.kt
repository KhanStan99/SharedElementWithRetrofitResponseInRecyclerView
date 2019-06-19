package com.trentweet.fragmenttransition.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
        current: Fragment,
        newFragment: Fragment,
        tag: String,
        sharedView: View,
        sharedElementName: String
    ) {
//        val fragmentManager = supportFragmentManager
//        // check if the fragment is in back stack
//        val fragmentPopped = fragmentManager.popBackStackImmediate(tag, 0)
//        if (fragmentPopped) {
//            // fragment is pop from backStack
//        } else {
//
//            current.sharedElementReturnTransition =
//                TransitionInflater.from(this).inflateTransition(R.transition.default_transition)
//            current.exitTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition)
//
//            newFragment.sharedElementEnterTransition =
//                TransitionInflater.from(this).inflateTransition(R.transition.default_transition)
//            newFragment.enterTransition =
//                TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition)
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.content, newFragment, tag)
//            fragmentTransaction.addToBackStack(tag)
//            fragmentTransaction.addSharedElement(sharedView, sharedElementName)
//            fragmentTransaction.commit()
//        }

        supportFragmentManager
            .beginTransaction()
            .addSharedElement(sharedView, sharedElementName)
            .replace(R.id.content, newFragment, tag)
            .addToBackStack(tag)
            .commit()
    }


}
