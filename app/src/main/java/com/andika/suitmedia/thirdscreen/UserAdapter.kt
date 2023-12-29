package com.andika.suitmedia.thirdscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andika.suitmedia.data.User
import com.andika.suitmedia.databinding.ItemUserBinding

class UserAdapter(private val context:Context,private var userList: MutableList<User>) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding,context)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateData(newData: List<User>) {
        userList.addAll(newData)
        notifyDataSetChanged()
    }
}

