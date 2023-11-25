package ru.trailblazers.musicapp.data

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author nvoxel
 */
object AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}
