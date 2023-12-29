package com.andika.suitmedia.thirdscreen

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.andika.suitmedia.data.User
import com.andika.suitmedia.databinding.ItemUserBinding
import com.bumptech.glide.Glide

class UserViewHolder(private val binding: ItemUserBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User) {
        binding.user = user
        Glide.with(context)
            .load(user.avatar)
            .into(binding.imageViewUser)
        binding.executePendingBindings()
    }
}
