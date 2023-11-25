package ru.trailblazers.musicapp.data.models

/**
 * @author nvoxel
 */
data class Track(
    val id: Long,
    val name: String,
    val artist: String,
    val durationMillis: Long,
)
