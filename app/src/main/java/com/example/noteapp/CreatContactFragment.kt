package com.example.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.example.noteapp.databinding.FragmentCreatContactBinding


class CreatContactFragment :Fragment(){

    lateinit var binding: FragmentCreatContactBinding
    lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentCreatContactBinding.inflate(inflater,container,false)

        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java,
            "Contact-Database"
        ).allowMainThreadQueries().build()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOrder.setOnClickListener {
            val name = binding.eT1.text.toString().trim()
            val email = binding.eT2.text.toString().trim()
            val phone = binding.eT3.text.toString().trim()

            val user = User(name= name,email = email,phone = phone)
            db.userDao().insert(user)


        }
    }

}