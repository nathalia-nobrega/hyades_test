package com.example.test_hyades

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.test_hyades.fragments.ExplorarFragment
import com.example.test_hyades.fragments.InicioFragment
import com.example.test_hyades.fragments.PerfilFragment
import com.example.test_hyades.fragments.SalvosFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class NavigateFragments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigate_fragments)
        createFragments()
    }

    private fun createFragments() {
        val inicioFragment = InicioFragment()
        val explorarFragment = ExplorarFragment(applicationContext)
        val salvosFragment = SalvosFragment()
        val perfilFragment = PerfilFragment()

        setCurrentFragment(inicioFragment)

        findViewById<BottomNavigationView>(R.id.bottomNavigationView3).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuHome -> setCurrentFragment(inicioFragment)
                R.id.menuExplore -> setCurrentFragment(explorarFragment)
                R.id.menuBookmark -> setCurrentFragment(salvosFragment)
                R.id.menuProfile -> setCurrentFragment(perfilFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            addToBackStack(null)
            commit()
        }
}