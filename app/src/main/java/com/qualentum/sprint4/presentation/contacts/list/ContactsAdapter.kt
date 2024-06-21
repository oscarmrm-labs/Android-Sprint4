package com.qualentum.sprint4.presentation.contacts.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qualentum.sprint4.R
import com.qualentum.sprint4.domain.model.ContactModel

class ContactsAdapter(
    val itemList: List<ContactModel>?,
    private val onClickListener: (ContactModel?) -> Unit
) :
    RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(layoutInflater.inflate(R.layout.row_contact, parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = itemList?.get(position)
        holder.onBind(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return itemList?.size ?: 0
    }

}