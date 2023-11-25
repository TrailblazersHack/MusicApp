package ru.trailblazers.musicapp.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

/**
 * @author nvoxel
 */
class PlayerScreen(
    private val roomId: Long
) : Screen {

    @Composable
    override fun Content() {
        Text(text = "Комната")
    }
}
