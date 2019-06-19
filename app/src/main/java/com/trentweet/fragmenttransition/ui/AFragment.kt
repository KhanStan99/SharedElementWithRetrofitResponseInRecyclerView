package com.trentweet.fragmenttransition.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trentweet.fragmenttransition.ClassAdapter
import com.trentweet.fragmenttransition.R
import com.trentweet.fragmenttransition.data.UserListModel
import com.trentweet.fragmenttransition.data.UserResponseModel
import com.trentweet.fragmenttransition.service.ApiInterface
import com.trentweet.fragmenttransition.service.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AFragment : Fragment() {

    private lateinit var mView: View
    private val service = RetrofitClientInstance.retrofitInstance?.create(ApiInterface::class.java)

    private var results: List<UserListModel>? = null

    override fun onStart() {
        super.onStart()
        if (results == null) {
            mView.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
            val getAllUsersList = service?.getAllUsers()

            getAllUsersList?.enqueue(object : Callback<UserResponseModel> {
                override fun onFailure(call: Call<UserResponseModel>, t: Throwable) {
                    logInfo(t.localizedMessage)
                    mView.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                }

                override fun onResponse(call: Call<UserResponseModel>, response: Response<UserResponseModel>) {
                    results = response.body()!!.data
                    mView.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                    updateRecyclerView(results!!)
                }
            })
        } else {
            mView.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
            updateRecyclerView(results!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_a, container, false)



        return mView
    }

    private fun logInfo(msg: String) {
        Log.e("AFragment: ", msg)
    }

    private fun updateRecyclerView(results: List<UserListModel>) {
        val adapter = ClassAdapter(context!!, results, this)
        mView.findViewById<RecyclerView>(R.id.user_rv).layoutManager = LinearLayoutManager(context)
        mView.findViewById<RecyclerView>(R.id.user_rv).adapter = adapter
    }

    private fun toast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun gotoNextPage(
        currentItem: UserListModel,
        imageView: View,
        transitionName: String
    ) {

        val bundle = Bundle()
        val simpleFragmentB = BFragment.newInstance()

        bundle.putString("transitionName", transitionName)
        bundle.putSerializable("movie", currentItem)
        simpleFragmentB.arguments = bundle

        (activity as MainActivity).showFragmentWithTransition(
            this, simpleFragmentB, "movieDetail", imageView,
            transitionName
        )
    }

    companion object {
        fun newInstance(): AFragment {
            return AFragment()
        }
    }
}