package com.example.test_hyades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.test_hyades.R
import com.example.test_hyades.fragments.SalvosFragment
import com.google.android.material.textfield.TextInputLayout

class CreatePlaylist : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_playlist)

        val btn = findViewById<Button>(R.id.filledButton).setOnClickListener {

            val mFragmentManager = supportFragmentManager
            val mFragmentTransaction = mFragmentManager.beginTransaction()
            val myFragment = SalvosFragment()

            var playlistNome = findViewById<TextInputLayout>(R.id.playlistField).editText?.text.toString()
            if (playlistNome.isNotEmpty()) {
                val bundle = Bundle()
                myFragment.arguments = bundle
                bundle.putString("message", playlistNome)
                mFragmentTransaction.add(R.id.salvosFrag, myFragment).commit()
                val intent = Intent(this, SalvosFragment::class.java)
                startActivity(intent)
            }
        }


    }
}