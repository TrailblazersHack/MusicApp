package ru.trailblazers.musicapp.data.models

import kotlinx.serialization.Serializable

/**
 * @author nvoxel
 */
@Serializable
data class Track(
    val id: Long,
    val name: String,
    val artist: String,
    val durationMillis: Long,
    val url: String
)
