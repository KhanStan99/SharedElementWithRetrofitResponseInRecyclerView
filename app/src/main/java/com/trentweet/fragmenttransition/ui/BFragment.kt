package com.trentweet.fragmenttransition.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.trentweet.fragmenttransition.R
import com.trentweet.fragmenttransition.data.UserListModel


class BFragment : Fragment() {
    private lateinit var mView: View

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_b, container, false)

        val b = arguments
        if (b != null) {
            val movie = b.getSerializable("model") as UserListModel
            mView.findViewById<ImageView>(R.id.fragment_b_image).transitionName =
                b.getString("transitionName")

            val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .error(R.mipmap.ic_launcher)
            Glide.with(context!!)
                .load(movie.avatar)
                .apply(requestOptions)
                .into(mView.findViewById(R.id.fragment_b_image))

            mView.findViewById<TextView>(R.id.fragment_b_text).text =
                "${movie.first_name}  ${movie.last_name}, ${movie.email}"

        }
        return mView
    }


    companion object {
        fun newInstance(): BFragment {
            return BFragment()
        }
    }
}


