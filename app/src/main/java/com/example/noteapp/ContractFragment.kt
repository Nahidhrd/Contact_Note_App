package com.example.noteapp

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.noteapp.databinding.FragmentContractBinding


class ContractFragment:Fragment(),ContactAdapter.Listener{

    lateinit var binding: FragmentContractBinding
    lateinit var adapter: ContactAdapter
    lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContractBinding.inflate(inflater,container,false)
        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java,
            "Contact-Database"
        ).allowMainThreadQueries().build()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.createContactBtn.setOnClickListener {
            findNavController().navigate(R.id.action_contractFragment_to_creatContactFragment)

        }

        setDataToUi()
    }

    private fun setDataToUi() {
        adapter= ContactAdapter(db.userDao().getAll(),this)
        binding.contactrcv.adapter = adapter

    }

    override fun onContactDelete(Contact: User) {

        val alertDialog= AlertDialog.Builder(requireContext())
            .setTitle("Are you sure?")
            .setMessage("want to delete this?")
            .setIcon(R.drawable.baseline_add_alert_24)


            .setPositiveButton("Delete") { p0, p1 ->
                db.userDao().delete(Contact)
                Log.d("TAG", "onContactDelete:${Contact.toString()} is deleted")
                setDataToUi()
            }

            .setNegativeButton("Dismiss"
            ) { p0, p1 -> }
        val alerts= alertDialog.create()
        alerts.show()
    }

}