package ru.trailblazers.musicapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

/**
 * @author nvoxel
 */
class RoomsScreen : Screen {

    @Composable
    override fun Content() {
        Column {
            Text(text = "Комнаты")

            val navigator = LocalNavigator.current
            Button(onClick = { navigator?.push(PlayerScreen()) }) {
                Text(text = "Войти в комнату")
            }
        }
    }
}
