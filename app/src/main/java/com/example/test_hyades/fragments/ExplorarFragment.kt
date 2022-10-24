package com.example.test_hyades.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_hyades.ExplorarAdapter
import com.example.test_hyades.R
import com.example.test_hyades.database.DbDatabase
import com.example.test_hyades.database.DbRepository

class ExplorarFragment(private val contexto: Context) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var explorarFilmAdapter: ExplorarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explorar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //código pro bd
        val repository = DbRepository(contexto)
        val data = DbDatabase.getDirectedByWomen()

        data.forEach {
            repository.insert(it)
        }
        //código pro recyclerview

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerFilmesExplorar)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        explorarFilmAdapter = ExplorarAdapter(requireContext(), repository.search())
        recyclerView.adapter = explorarFilmAdapter

    }
}