package ru.trailblazers.musicapp.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.trailblazers.musicapp.data.models.Room
import ru.trailblazers.musicapp.data.models.Track
import ru.trailblazers.musicapp.data.models.User

/**
 * @author nvoxel
 */
interface MusicApi {

    @GET("tracks")
    fun getTrack(@Query("trackId") trackId: Long): Track

    @GET("users")
    fun getUser(@Query("userId") userId: Long): User

    @GET("rooms")
    fun getRoom(@Query("roomId") roomId: Long): Room

    @GET("rooms")
    fun getRooms(): List<Room>

    @POST("rooms")
    fun createRoom(@Body room: Room)
}
