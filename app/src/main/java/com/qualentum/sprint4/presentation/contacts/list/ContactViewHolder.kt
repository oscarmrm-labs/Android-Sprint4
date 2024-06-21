package com.qualentum.sprint4.presentation.contacts.list

import com.qualentum.sprint4.domain.model.ContactModel
import com.qualentum.sprint4.databinding.RowContactBinding
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = RowContactBinding.bind(view)

    fun onBind(contact: ContactModel?, onClickListener: (ContactModel?) -> Unit) {
        binding.tvName.text = contact?.name.toString()
        binding.tvAge.text = contact?.lastName.toString()
        itemView.setOnClickListener {
            onClickListener(contact)
        }
    }
}