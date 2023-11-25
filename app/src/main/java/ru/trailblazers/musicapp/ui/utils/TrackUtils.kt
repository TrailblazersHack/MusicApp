package ru.trailblazers.musicapp.ui.utils

import android.text.format.DateUtils
import kotlin.math.roundToLong

/**
 * @author nvoxel
 */
class TrackUtils {

    companion object {
        fun songDurationToString(durationMillis: Long): String =
            DateUtils.formatElapsedTime(durationMillis / 1000)

        fun songDurationToString(durationSeconds: Float): String =
            DateUtils.formatElapsedTime(durationSeconds.roundToLong())
    }
}
