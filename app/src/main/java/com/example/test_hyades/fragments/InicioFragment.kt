package com.example.test_hyades.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_hyades.InicioAdapter
import com.example.test_hyades.R
import com.example.test_hyades.database.DbDatabase
import com.example.test_hyades.database.DbRepository

class InicioFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var inicioFilmAdapter: InicioAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = DbDatabase.getRecFilms()
        val repository = DbRepository(requireContext())

        data.forEach {
            try {
                repository.insert(it)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerFilmes)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        inicioFilmAdapter = InicioAdapter(requireContext(), data)
        recyclerView.adapter = inicioFilmAdapter
    }

}