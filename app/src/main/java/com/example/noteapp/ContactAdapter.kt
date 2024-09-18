package com.example.noteapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.databinding.ContactMainBinding
import java.util.jar.Attributes.Name


class ContactAdapter(val contactList : List<User>,val listener: Listener) :
RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){

    interface Listener{
        fun onContactDelete(user: User)
    }


    class ContactViewHolder(var binding: ContactMainBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
       return ContactViewHolder(
           ContactMainBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           )
       )
    }

    override fun getItemCount(): Int {
      return contactList.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        contactList[position].let {contact ->

            holder.binding.apply {
            nameTv.text = contact.name
            emailTv.text= contact.email
            phoneTv.text= contact.phone
            }
          holder.binding.root.setOnLongClickListener {
              Log.d("TAG", "Long Clicked:${contact.uid} ")
              listener.onContactDelete(contact)

              true
           }
        }
     }
}