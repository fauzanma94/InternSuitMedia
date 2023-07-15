package com.example.mysuitmediaintern.ui.third

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mysuitmediaintern.R
import com.example.mysuitmediaintern.api.User
import com.example.mysuitmediaintern.databinding.ItemUserBinding
import com.example.mysuitmediaintern.ui.second.SecondScreenFragment
import com.example.mysuitmediaintern.ui.second.SecondScreenFragmentDirections

class ThirdScreenAdapter :
    RecyclerView.Adapter<ThirdScreenAdapter.ViewHolder>() {

    private val listUser: MutableList<User> = mutableListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<User>) {
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            binding.apply {
                itemEmail.text = user.email
                itemName.text = "${user.firstName} ${user.lastName}"
                Glide.with(itemView)
                    .load(user.avatar)
                    .into(itemPhoto)
                itemView.setOnClickListener {
                    val bundle = Bundle().apply {
                        putString("first_name", user.firstName)
                        putString("last_name", user.lastName)
                    }

                    itemView.findNavController()
                        .navigate(R.id.action_thirdScreenFragment_to_secondScreenFragment, bundle)
                }


            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listUser.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)

    }
}