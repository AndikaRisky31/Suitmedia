package com.andika.suitmedia.thirdscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andika.suitmedia.data.User
import com.andika.suitmedia.databinding.ItemUserBinding
import com.bumptech.glide.Glide


class UserAdapter(private val context: Context, private var userList: MutableList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: ThirdScreenActivity) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding, context, userList, listener)
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
    class UserViewHolder(
        private val binding: ItemUserBinding,
        private val context: Context,
        private val userList: List<User>, // assuming you need this list for some other reason
        private val listener: OnItemClickListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user

            Glide.with(context)
                .load(user.avatar)
                .into(binding.imageViewUser)
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = userList[position] // Assuming you need this list for some other reason
                    listener?.onItemClick(clickedItem)
                }
            }

            binding.executePendingBindings()
        }
    }
}

