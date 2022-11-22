package com.example.test_hyades.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.test_hyades.MainActivity
import com.example.test_hyades.R
import com.example.test_hyades.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.w3c.dom.Text

class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*start displaying the user's data*/

        //ui
        val mName = view.findViewById<TextView>(R.id.mNome)
        val mEmail = view.findViewById<TextView>(R.id.mEmail)
        val btnUpdate = view.findViewById<Button>(R.id.btnUpdate)
        val btnLogout =  view.findViewById<TextView>(R.id.logout)

        val reference = FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile: User = snapshot.getValue(User::class.java)!!
                val username = userProfile.name
                val email = userProfile.email

                mName.text = username
                mEmail.text = email

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", error.message)
            }

        })
        btnUpdate.setOnClickListener {
            val fragment = UpdateProfileFragment()
            val frManager = activity?.supportFragmentManager
            val frTransaction = frManager?.beginTransaction()

            frTransaction?.apply {
                replace(R.id.perfilFragment, fragment)
                addToBackStack(null)
                commit()
            }
        }

        /*end update user's data*/

        btnLogout.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            auth.signOut()

            activity?.let {
                val intent = Intent(it, MainActivity::class.java)
                it.startActivity(intent)
            }
        }

    }

}

