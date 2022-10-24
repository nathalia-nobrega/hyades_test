package com.example.test_hyades.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log
import com.example.nathalia_hyades.model.Film

class DbRepository(context: Context) {

    private lateinit var db: SQLiteDatabase
    private var dbHelper: DbHelper

    init {
        dbHelper = DbHelper(context)
    }

    //Colocar informações no banco de dados
    fun insert (
        film: Film
    ) : Long {
        db = dbHelper.writableDatabase
        val cv = ContentValues().apply {
            put(DbClass.FilmeEntry.TITULO_COLUMN, film.film_title)
            put(DbClass.FilmeEntry.DIRETOR_COLUMN, film.film_director)
            put(DbClass.FilmeEntry.ANO_COLUMN, film.film_year)
            put(DbClass.FilmeEntry.DURACAO_COLUMN, film.film_duration)
            put(DbClass.FilmeEntry.TOPICS_COLUMN, film.key_topics)
        }
        try {
            val id = db.replace(DbClass.FilmeEntry.FILME_TABLE, null, cv)
            db.close()
            return id
        } catch (e: Exception) {
            Log.println(Log.ASSERT, "Error: ", e.toString())
        }
        return -1
    }

    //Ler informações do banco de dados
    fun search(): ArrayList<Film> {
        val films = arrayListOf<Film>()

        val sql = "SELECT * FROM ${DbClass.FilmeEntry.FILME_TABLE}"
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(sql, null)

        with(cursor) {
            while (this?.moveToNext()!!) {
                val filmTitle = getString(getColumnIndexOrThrow(DbClass.FilmeEntry.TITULO_COLUMN))
                val filmDirector = getString(getColumnIndexOrThrow(DbClass.FilmeEntry.DIRETOR_COLUMN))
                val filmYear = getInt(getColumnIndexOrThrow(DbClass.FilmeEntry.ANO_COLUMN))
                val filmDuration = getInt(getColumnIndexOrThrow(DbClass.FilmeEntry.DURACAO_COLUMN))
                val filmTopics = getString(getColumnIndexOrThrow(DbClass.FilmeEntry.TOPICS_COLUMN))

                val film = Film(filmTitle, filmDirector, filmYear, filmDuration, filmTopics)
                films.add(film)
            }
        }
        cursor?.close()
        db.close()
        return films
    }

    fun delete (vararg filmes: Film) {
        db = dbHelper.writableDatabase
        for (filme in filmes) {
            db.delete(
                DbClass.FilmeEntry.FILME_TABLE,
                "${BaseColumns._ID} = ?",
                arrayOf(filme.film_title)
            )
        }
        db.close()
    }

    fun deleteAll () {
        db = dbHelper.writableDatabase
        db.execSQL("delete from ${DbClass.FilmeEntry.FILME_TABLE}")
        db.close()
    }

    fun close() {
        dbHelper.close()
    }
}