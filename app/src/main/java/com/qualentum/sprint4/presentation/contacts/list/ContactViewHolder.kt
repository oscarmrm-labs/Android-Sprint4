package com.qualentum.sprint4.presentation.contacts.list

import android.content.Context
import com.qualentum.sprint4.domain.model.ContactModel
import com.qualentum.sprint4.databinding.RowContactBinding
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.qualentum.sprint4.R

class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = RowContactBinding.bind(view)
    private val context: Context = view.context

    fun onBind(contact: ContactModel?, onClickListener: (ContactModel?) -> Unit, onFavouriteClick: (ContactModel?) -> Unit, onDeleteClick: (ContactModel?) -> Unit) {
        binding.apply {
            tvName.text = contact?.name.toString()
            tvLastName.text = contact?.lastName.toString()
            icAddFavouriteInside.setColorFilter(getStarColor(contact?.isFavourite), android.graphics.PorterDuff.Mode.SRC_IN)
            icAddFavouriteHolder.setOnClickListener { onFavouriteClick(contact) }
            icDeleteContact.setOnClickListener { onDeleteClick(contact) }
        }
        itemView.setOnClickListener { onClickListener(contact) }
    }

    private fun getStarColor(isFavourite: Boolean?): Int {
        return if (isFavourite == true) {
            ContextCompat.getColor(context, R.color.colorPrimary)
        } else {
            ContextCompat.getColor(context, R.color.white)
        }
    }
}