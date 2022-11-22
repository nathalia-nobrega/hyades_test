package com.example.test_hyades.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.test_hyades.R
import com.example.test_hyades.model.User
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UpdateProfileFragment : Fragment() {

    private var reference = FirebaseDatabase.getInstance().getReference("Users")
        .child(FirebaseAuth.getInstance().currentUser!!.uid)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_update_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //ui
        val btn = view.findViewById<Button>(R.id.filledButton)
        val editUser = view.findViewById<TextInputLayout>(R.id.userField)
        val editEmail = view.findViewById<TextInputLayout>(R.id.emailField)
        val editPass = view.findViewById<TextInputLayout>(R.id.senhaField)

            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userProfile: User = snapshot.getValue(User::class.java)!!
                    val username = userProfile.name
                    val email = userProfile.email
                    val password = userProfile.password

                    btn.setOnClickListener {
                        updateProfile(username, email, password, editUser, editEmail, editPass)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

    }


    private fun updateProfile(
        username: String?,
        email: String?,
        password: String?,
        editUser: TextInputLayout,
        editEmail: TextInputLayout,
        editPass: TextInputLayout
    ) {

        if (isUserChanged(username, editUser) ||
            isEmailChanged(email, editEmail) ||
            isPassChanged(password, editPass)) {
            Toast.makeText(requireContext(), "Seu perfil foi atualizado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Os dados continuam os mesmos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isPassChanged(password: String?, editPass: TextInputLayout): Boolean {
        if (password != editPass.editText?.text.toString() && editPass.editText?.text.toString().isNotBlank()) {
            if (password != null) {
                reference.child("password").setValue(editPass.editText?.text.toString())
                editPass.clearFocus()
                //atualizar dados no auth também
                val user = FirebaseAuth.getInstance().currentUser

                if (user != null) {
                    user.updatePassword(editPass.editText?.text.toString())
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d(TAG, "User password updated.")
                            } else {
                                var e = task.exception;
                                if (e != null) {
                                    Toast.makeText (requireContext(), "Error updating email: "+e.message, Toast.LENGTH_SHORT).show()
                                };
                                Log.w("updateEmail", "Unable to update email", e);
                            }
                        }
                }
            }
            return true
        } else {
            return false
        }
    }

    private fun isEmailChanged(email: String?, editEmail: TextInputLayout): Boolean {
        if (email != editEmail.editText?.text.toString() && editEmail.editText?.text.toString().isNotBlank()) {
            if (email != null) {
                reference.child("email").setValue(editEmail.editText?.text.toString())
                editEmail.clearFocus()
                //atualizando dados no auth do firebase
                val user = FirebaseAuth.getInstance().currentUser

                if (user != null) {
                    user.updateEmail(editEmail.editText?.text.toString())
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d(TAG, "User email address updated.")
                            } else {
                                var e = task.exception;
                                if (e != null) {
                                    Toast.makeText (requireContext(), "Error updating email: "+e.message, Toast.LENGTH_SHORT).show()
                                };
                                Log.w("updateEmail", "Unable to update email", e);
                            }
                        }
                }
            }
            return true
        } else {
            return false
        }
    }

    private fun isUserChanged(username: String?, editUser: TextInputLayout): Boolean {
        if (username != editUser.editText?.text.toString() && editUser.editText?.text.toString().isNotBlank()) {
            if (username != null) {
                reference.child("name").setValue(editUser.editText?.text.toString())
                editUser.clearFocus()

            }
            return true
        } else {
            return false
        }

    }


}