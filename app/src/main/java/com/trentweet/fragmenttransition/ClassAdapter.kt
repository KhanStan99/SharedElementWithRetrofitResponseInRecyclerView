package com.trentweet.fragmenttransition

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.trentweet.fragmenttransition.data.UserListModel
import com.trentweet.fragmenttransition.ui.AFragment

class ClassAdapter(
    private val context: Context,
    private val arrayList: List<UserListModel>,
    private val aFragment: AFragment
) :
    RecyclerView.Adapter<ClassAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_recycler_view, p0, false)
        )
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var userCV: CardView = view.findViewById(R.id.user_cv)
        var imageView: ImageView = view.findViewById(R.id.userImage)
        var name: TextView = view.findViewById(R.id.f_name)
        var email: TextView = view.findViewById(R.id.email)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = arrayList[position]
        holder.email.text = currentItem.email
        holder.name.text = "${currentItem.first_name} ${currentItem.last_name}"
        holder.imageView.transitionName = "transition$position"

        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .transform(CircleTransform(context))
            .error(R.mipmap.ic_launcher)
        Glide.with(context)
            .load(currentItem.avatar)
            .apply(requestOptions)
            .into(holder.imageView)

        holder.userCV.setOnClickListener {
            aFragment.gotoNextPage(currentItem, holder.imageView, holder.imageView.transitionName)
        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}
