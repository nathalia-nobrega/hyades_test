package com.example.test_hyades.database

import com.example.nathalia_hyades.model.Film

class DbDatabase {
    companion object {
        fun getRecFilms() = mutableListOf(
            Film(
                "NOPE",
                "Jordan Peele",
                2022,
                98,
                "I don't know"
            ),

            Film(
                "O Quinto Selo",
                "Zoltán Fábri",
                1976,
                101,
                "Guerra"
            ),

            Film(
                "O Funeral das Rosas",
                "Toshio Matsumoto",
                1969,
                120,
                "LGBTQIA+"
            ),

            Film(
                "Nossas Crianças",
                "Dag Johan Haugerud",
                2019,
                237,
                "Sociedade Atual, Política"
            ),

            Film(
                "Hiroshima",
                "Hideo Sekigawa",
                1953,
                145,
                "Guerra, Drama Humano"
            ),

            Film(
                "Minha Vida de Abobrinha",
                "Claude Barras",
                2016,
                106,
                "Famílias Complexas, Infância, Abandono"
            ),
        )

        fun getDirectedByWomen() = mutableListOf(
            Film(
                "Os Belos Cílios de Lauri Mantyvaara",
                "Hannaleena Hauru",
                2017,
                114,
                "Questão feminina, juventude"
            ),
            Film(
                "Você Nunca Esteve Realmente Aqui",
                "Lynne Ramsay",
                2017,
                124,
                "Prostituição"
            ),
            Film(
                "Jeanne Dielman, 23, quai du Commerce, 1080 Bruxelles",
                "Chantal Akerman",
                1975,
                189,
                "Questão feminina, vida doméstica"
            ),
            Film(
                "Quando Éramos Bruxas",
                "Nietzchka Keene",
                1990,
                98,
                "Questão feminina"
            ),
            Film(
                "O Piano",
                "Jane Campion",
                1993,
                121,
                "Questão feminina"
            )
        )
    }
}