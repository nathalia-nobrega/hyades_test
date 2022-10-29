package com.example.test_hyades.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test_hyades.CreatePlaylist
import com.example.test_hyades.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SalvosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salvos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = view.findViewById<FloatingActionButton>(R.id.floating_action_button).setOnClickListener {
            activity.let {
                val intent = Intent(it, CreatePlaylist::class.java)
                startActivity(intent)
            }
            val bundle = arguments
            val message = bundle!!.getString("message")
        }
    }


}