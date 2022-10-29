package com.example.test_hyades

import com.example.test_hyades.model.Playlist

object PlaylistController {

    private val PLAYLIST: MutableList<Playlist> = arrayListOf()

    fun createPlaylist (playlist: Playlist) {
        PLAYLIST.add(playlist)
    }

    fun listOfPlaylists() = PLAYLIST
}