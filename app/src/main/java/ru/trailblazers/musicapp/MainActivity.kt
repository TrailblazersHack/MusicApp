package ru.trailblazers.musicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import ru.trailblazers.musicapp.ui.screens.AuthScreen
import ru.trailblazers.musicapp.ui.screens.MainScreen
import ru.trailblazers.musicapp.ui.theme.MusicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val needAuth = true

        setContent {
            MusicAppTheme {
                Navigator(
                    if (needAuth) AuthScreen()
                    else MainScreen()
                )
            }
        }
    }
}
