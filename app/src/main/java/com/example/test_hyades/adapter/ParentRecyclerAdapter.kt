package com.example.test_hyades.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_hyades.R
import com.example.test_hyades.model.Playlist

class ParentRecyclerAdapter(private val context: Context, private val list: MutableList<Playlist>)
    : RecyclerView.Adapter<ParentRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNome = view.findViewById<TextView>(R.id.List_name)
        val rvChild = view.findViewById<RecyclerView>(R.id.ChildRecView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.playlist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lista: Playlist = list[position]
        holder.txtNome.text = lista.nome

        val layoutManager = LinearLayoutManager(context)
        holder.rvChild.layoutManager = layoutManager
        holder.rvChild.setHasFixedSize(true)
        val arrayList: ArrayList<Playlist> = ArrayList()
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}