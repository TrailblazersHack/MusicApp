package ru.trailblazers.musicapp.ui.screens.bottomnav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import ru.trailblazers.musicapp.ui.screens.RoomsScreen
import ru.trailblazers.musicapp.ui.theme.Groups

/**
 * @author nvoxel
 */
object MainTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = Groups)

            return remember {
                TabOptions(
                    index = 1u,
                    title = "Комнаты",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(RoomsScreen())
    }
}
