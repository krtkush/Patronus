package com.krtkush.patronus.feature.deviceholder.list.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.krtkush.patronus.R
import com.krtkush.patronus.datasource.remote.rest.model.list.Customer
import com.krtkush.patronus.databinding.UserListItemBinding

class DeviceHolderListAdapter(
    private val listener: DeviceHolderItemOnClickListener,
    private val userList: List<Customer>
    ) : RecyclerView.Adapter<UserItemViewHolder>() {

    interface DeviceHolderItemOnClickListener {
        fun onDeviceHolderItemSelected(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val binding = UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserItemViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val item = userList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = userList.size
}

class UserItemViewHolder(
        private val itemBinding : UserListItemBinding,
        private val listener: DeviceHolderListAdapter.DeviceHolderItemOnClickListener
    ) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private lateinit var user: Customer

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(userItem : Customer) {

        this.user = userItem

        itemBinding.userFullName.text = "${userItem.firstName} ${userItem.lastName}"
        itemBinding.userGender.text = userItem.gender
        itemBinding.userPhoneNumber.text = userItem.phoneNumber

        if (userItem.stickers.contains("Fam")) {
            itemBinding.famTag.text = itemBinding.famTag.context.getText(R.string.tag_text_fam)
            itemBinding.famTag.visibility = View.VISIBLE
        } else {
            itemBinding.famTag.visibility = View.GONE
        }

        if (userItem.stickers.contains("Ban")) {
            itemBinding.banTag.text = itemBinding.banTag.context.getText(R.string.tag_text_ban)
            itemBinding.banTag.visibility = View.VISIBLE
        } else {
            itemBinding.banTag.visibility = View.GONE
        }

        itemBinding.imageAlternativeTV.visibility = View.GONE
        itemBinding.userImage.visibility = View.VISIBLE

        Glide.with(itemView.context)
            .load(userItem.imageUrl)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    setImageFailAlternative(itemBinding, userItem)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(itemBinding.userImage)
    }

    private fun setImageFailAlternative(itemBinding : UserListItemBinding, userItem : Customer) {

        itemBinding.imageAlternativeTV.visibility = View.VISIBLE
        itemBinding.userImage.visibility = View.INVISIBLE
        itemBinding.imageAlternativeTV.text = "${userItem.firstName[0]}${userItem.lastName[0]}"
    }

    override fun onClick(p0: View?) {
        listener.onDeviceHolderItemSelected(user.id)
    }
}