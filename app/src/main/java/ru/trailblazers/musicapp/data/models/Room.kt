package ru.trailblazers.musicapp.data.models

/**
 * @author nvoxel
 */
data class Room(
    val id: Long,
    val name: String,
    val participants: Int,
    val host: User
)
