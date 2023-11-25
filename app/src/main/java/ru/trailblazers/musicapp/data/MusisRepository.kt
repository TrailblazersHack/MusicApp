package ru.trailblazers.musicapp.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.trailblazers.musicapp.data.models.Room
import ru.trailblazers.musicapp.data.models.Track
import ru.trailblazers.musicapp.data.models.User

/**
 * @author nvoxel
 */
object MusisRepository {

    val musicApi = Retrofit.Builder()
        .baseUrl("http://10.242.242.160:8080/")
        .client(
            OkHttpClient.Builder()
                .addNetworkInterceptor(AuthInterceptor)
                .build()
        )
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()
        .create(MusicApi::class.java)

    fun getTrack(trackId: Long): Track = musicApi.getTrack(trackId)

    fun getUser(userId: Long): User = musicApi.getUser(userId)

    fun getRoom(roomId: Long): Room = musicApi.getRoom(roomId)

    fun getRooms(): List<Room> = musicApi.getRooms()

    fun createRoom(room: Room) = musicApi.createRoom(room)
}
