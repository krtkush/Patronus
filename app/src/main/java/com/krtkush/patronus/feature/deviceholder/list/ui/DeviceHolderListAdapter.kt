package com.krtkush.patronus.feature.deviceholder.list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krtkush.patronus.data.models.user.list.Customer
import com.krtkush.patronus.databinding.UserListItemBinding

class DeviceHolderListAdapter(private val userList: List<Customer>) : RecyclerView.Adapter<DeviceHolderListAdapter.UserItemViewHolder>() {

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val binding = UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val item = userList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = userList.size

    inner class UserItemViewHolder(private val binding : UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : Customer) {
            binding.userListLayout.setOnClickListener {
                listener!!.onItemClick(item.id)
            }
        }
    }
}