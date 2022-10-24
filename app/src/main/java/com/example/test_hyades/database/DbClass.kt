package com.example.test_hyades.database

import android.provider.BaseColumns

object DbClass {
    object FilmeEntry : BaseColumns {
        const val FILME_TABLE = "filme"
        const val TITULO_COLUMN = "titulo"
        const val DIRETOR_COLUMN = "diretor"
        const val CLASSIFICACAO_COLUMN = "classificacao"
        const val AVALICACOES_COLUMN = "avaliacoes"
        const val ANO_COLUMN = "ano"
        const val PAIS_COLUMN = "pais"
        const val DURACAO_COLUMN = "duracao"
        const val TOPICS_COLUMN = "topicos"
        const val FILME_POSTER = "poster"
    }

    const val SQL_CREATE_TABLE_FILME =
        "CREATE TABLE IF NOT EXISTS ${FilmeEntry.FILME_TABLE} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "${FilmeEntry.TITULO_COLUMN}  TEXT NOT NULL," +
                "${FilmeEntry.DIRETOR_COLUMN} TEXT NOT NULL," +
                "${FilmeEntry.CLASSIFICACAO_COLUMN} INTEGER," +
                "${FilmeEntry.AVALICACOES_COLUMN} REAL," +
                "${FilmeEntry.ANO_COLUMN} INTEGER NOT NULL," +
                "${FilmeEntry.PAIS_COLUMN} TEXT," +
                "${FilmeEntry.DURACAO_COLUMN} INTEGER NOT NULL," +
                "${FilmeEntry.TOPICS_COLUMN} TEXT NOT NULL," +
                "${FilmeEntry.FILME_POSTER} BLOB)"

    const val SQL_DELETE_TABLE_FILME = "DROP TABLE IF EXISTS ${FilmeEntry.FILME_TABLE}"
}