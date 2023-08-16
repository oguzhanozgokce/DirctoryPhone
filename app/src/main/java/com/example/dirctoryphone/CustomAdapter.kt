package com.example.dirctoryphone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dirctoryphone.databinding.UserItemListBinding

class CustomAdapter(
    private val onUserClick: (User) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val userList = arrayListOf<User>()

    class ViewHolder(
        private val binding: UserItemListBinding,
        private val onUserClick: (User) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            with(binding) {
                textViewNameSurname.text = user.nameSurname
                textViewage.text= user.age
                textViewAboutId.text=user.yourself

                root.setOnClickListener {
                    onUserClick(user)
                }
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding =
            UserItemListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding, onUserClick)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(userList[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = userList.size

    fun updateList(list: List<User>) {
        userList.clear()
        userList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }
}
