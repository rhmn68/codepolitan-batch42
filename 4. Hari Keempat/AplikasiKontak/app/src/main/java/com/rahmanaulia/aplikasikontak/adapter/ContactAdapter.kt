package com.rahmanaulia.aplikasikontak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahmanaulia.aplikasikontak.R
import com.rahmanaulia.aplikasikontak.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(private val listener: (Contact, Int) -> Unit)
    :RecyclerView.Adapter<ContactAdapter.ViewHolder>(){

    var listContacts = ArrayList<Contact>()
        set(listContacts){
            if (this.listContacts.size > 0){
                this.listContacts.clear()
            }
            this.listContacts.addAll(listContacts)
            notifyDataSetChanged()
        }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindItem(
            contact: Contact,
            position: Int,
            listener: (Contact, Int) -> Unit
        ) {
            itemView.tvNameContact.text = contact.name
            itemView.tvPhoneContact.text = contact.phone

            itemView.setOnClickListener {
                listener(contact, position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false))
    }

    override fun getItemCount(): Int {
        return listContacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listContacts[position], position, listener)
    }

    fun addItem(contact: Contact){
        this.listContacts.add(contact)
        notifyItemInserted(this.listContacts.size - 1)
    }

    fun updateItem(position: Int, contact: Contact){
        listContacts[position] = contact
        notifyItemChanged(position, contact)
    }

    fun removeItem(position: Int){
        listContacts.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listContacts.size)
    }
}