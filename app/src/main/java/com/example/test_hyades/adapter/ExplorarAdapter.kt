package com.example.test_hyades

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nathalia_hyades.model.Film
import com.example.test_hyades.R

// Adapter da RecyclerView do ExplorarFragment

class ExplorarAdapter(
    private val context: Context,
    private var filmList: MutableList<Film>
) : RecyclerView.Adapter<ExplorarAdapter.FilmViewHolder>() {

    inner class FilmViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val filmTitle: TextView = view.findViewById(R.id.txtFilmTitle)
        val filmDirector: TextView = view.findViewById(R.id.txtFilmDirector)
        val filmYear: TextView = view.findViewById(R.id.txtFilmYear)
        val filmDuration: TextView = view.findViewById(R.id.txtFilmDuration)
        val keyTopics: TextView = view.findViewById(R.id.txtKeyTopics)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_card, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film: Film = filmList[position]
        //implementar a imagem
        holder.filmTitle.text = film.film_title
        holder.filmDirector.text = film.film_director
        holder.filmYear.text = film.film_year.toString()
        holder.filmDuration.text = film.film_duration.toString()
        holder.keyTopics.text = film.key_topics
    }

    override fun getItemCount(): Int {
        return filmList.size
    }
}
