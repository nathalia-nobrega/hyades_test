package com.example.test_hyades

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nathalia_hyades.model.Film

// Adapter da RecyclerView do InicioFragment

class InicioAdapter(contexto: Context, private var filmList: MutableList<Film>) :

    RecyclerView.Adapter<InicioAdapter.FilmViewHolder>() {

    inner class FilmViewHolder(filmView: View) :
        RecyclerView.ViewHolder(filmView) {

        //var film_photo: ImageView = filmView.findViewById(R.id.imgFilmIcon)
        val filmTitle: TextView = filmView.findViewById(R.id.txtFilmTitle)
        val filmDirector: TextView = filmView.findViewById(R.id.txtFilmDirector)
        val filmYear: TextView = filmView.findViewById(R.id.txtFilmYear)
        val filmDuration: TextView = filmView.findViewById(R.id.txtFilmDuration)
        val keyTopics: TextView = filmView.findViewById(R.id.txtKeyTopics)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val filmView = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_card, parent, false)
        return FilmViewHolder(filmView)
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
        //Log.println(Log.ASSERT,"QTDE","${filmList.size}")
        return filmList.size

    }
}