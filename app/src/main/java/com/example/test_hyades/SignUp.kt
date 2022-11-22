package com.example.test_hyades

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.example.test_hyades.model.User
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var dbReference: DatabaseReference
    private lateinit var user: User
    private val USERS = "Users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()
        dbReference = db.getReference(USERS)

        findViewById<Button>(R.id.filledButton).setOnClickListener {
            val dName = findViewById<TextInputLayout>(R.id.userField).editText?.text.toString().trim()
            val email = findViewById<TextInputLayout>(R.id.emailField).editText?.text.toString().trim()
            val password = findViewById<TextInputLayout>(R.id.senhaField).editText?.text.toString().trim()

            if (dName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                //Se o e-mail for válido
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    createAccount(email, password)
                    user = User(dName, email, password)
                }

            }
        }
    }

    /* Criar conta */
    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    /* Redirecionar usuário após cadastro */
    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {

            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            val rootRef = FirebaseDatabase.getInstance().reference
            val usersRef = rootRef.child("Users")
            usersRef.child(uid).setValue(user)

            Toast.makeText(baseContext, "Conta criada com sucesso!",
                Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}