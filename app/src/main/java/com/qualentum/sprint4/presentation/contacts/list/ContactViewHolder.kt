package com.qualentum.sprint4.presentation.contacts.list

import com.qualentum.sprint4.data.model.Contact
import com.qualentum.sprint4.databinding.RowContactBinding
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = RowContactBinding.bind(view)

    fun onBind(contact: Contact?, onClickListener: (Contact?) -> Unit) {
        binding.tvName.text = contact?.name.toString()
        binding.tvAge.text = contact?.age.toString()
        itemView.setOnClickListener {
            onClickListener(contact)
        }
    }
}